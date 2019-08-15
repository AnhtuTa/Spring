package hello.controller;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hello.common.ResponseStatus;
import hello.exception.RestException;
import hello.model.Category;
import hello.model.SongInfo;

/**
 * @author tu.ta1 on 2019-04-20
 */
@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private Environment env;

    @GetMapping(value = "/all")
    public List<List<String>> getAllSong(HttpServletRequest request) throws IOException {
        List<List<String>> res = new ArrayList<>();
        String folderString = env.getProperty("mp3_folder");
        String[] folders = folderString.split(";");
        String name;

        for (String folder : folders) {
            List<String> mp3List = new ArrayList<>();
            File[] listOfFiles = new File(folder).listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    name = listOfFiles[i].getName();
                    if (name.toLowerCase().endsWith(".mp3"))
                        mp3List.add(listOfFiles[i].getName());
                }
            }

            if(mp3List.size() > 0) res.add(mp3List);
        }

        return res;
    }

    @GetMapping(value = "/all/by/folder")
    public List<Category> getAllSongByFolders() throws IOException {
        List<Category> res = new ArrayList<>();
        String folderString = env.getProperty("mp3_folder");
        String name;
        Category category;
        List<String> mp3List;

        File[] listOfFolders = new File(folderString).listFiles();

        for (int i = 0; i < listOfFolders.length; i++) {
            if(listOfFolders[i].isDirectory()) {
                mp3List = new ArrayList<>();
                File[] listOfFiles = new File(listOfFolders[i].getAbsolutePath()).listFiles();
                for (int j = 0; j < listOfFiles.length; j++) {
                    if (listOfFiles[j].isFile()) {
                        name = listOfFiles[j].getName();
                        if (name.toLowerCase().endsWith(".mp3")) {
                            mp3List.add(listOfFiles[j].getName());
                        }
                    }
                }

                if(mp3List.size() > 0) {
                    category = new Category();
                    category.setName(listOfFolders[i].getName());
                    category.setFileList(mp3List);
                    res.add(category);
                }
            }
        }

        return res;
    }

    /**
     * Return theo kiểu byte[] thì sẽ ko tua được file audio,
     * do đó phải dùng FileSystemResource
     * 
     * API này return về audio, thỉnh thoảng chạy nó có dấu hiệu bị xước!
     * Hiện tại chưa khắc phục được
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = { "audio/mpeg" })
    public FileSystemResource getSong(@RequestParam("file") String file) throws IOException {
        String folderString = env.getProperty("mp3_folder");

        File[] listOfFolders = new File(folderString).listFiles();
        for (int i = 0; i < listOfFolders.length; i++) {
            if(listOfFolders[i].isDirectory()) {
                File serverFile = new File(listOfFolders[i].getAbsolutePath() + File.separator + file);
                if (serverFile.exists())
                    //return Files.readAllBytes(serverFile.toPath());
                    return new FileSystemResource(serverFile);
            }
        }

        throw new RestException(ResponseStatus.MP3_NOT_FOUND);
    }

//    @GetMapping(value = "/url")
//    public String getSongURL(@RequestParam("file") String file) throws IOException {
//        String folderString = env.getProperty("mp3_folder");
//        String[] folders = folderString.split(";");
//
//        for (String folder : folders) {
//            File serverFile = new File(folder + File.separator + file);
//            if (serverFile.exists())
//                return folder + "/" + file;
//        }
//
//        throw new RestException(ResponseStatus.MP3_NOT_FOUND);
//    }

    /**
     * Reference: https://stackoverflow.com/a/21746415/7688028
     */
    @GetMapping(value = "/info")
    public SongInfo getAudioInfo(@RequestParam("file") String file) throws IOException {
        String folderString = env.getProperty("mp3_folder");
        String[] folders = folderString.split(";");
        SongInfo info = new SongInfo();

        for (String folder : folders) {
            File serverFile = new File(folder + File.separator + file);
            if (serverFile.exists()) {
                try {
                    InputStream input = new FileInputStream(serverFile);
                    ContentHandler handler = new DefaultHandler();
                    Metadata metadata = new Metadata();
                    Parser parser = new Mp3Parser();
                    ParseContext parseCtx = new ParseContext();
                    parser.parse(input, handler, metadata, parseCtx);
                    input.close();

                    info.setTitle(metadata.get("title"));
                    info.setArtist(metadata.get("xmpDM:artist"));
                    info.setAlbum(metadata.get("xmpDM:album"));

                } catch (FileNotFoundException e) {
                    throw new RestException(ResponseStatus.MP3_NOT_FOUND);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (TikaException e) {
                    e.printStackTrace();
                }
            }
        }

        return info;
    }
}
