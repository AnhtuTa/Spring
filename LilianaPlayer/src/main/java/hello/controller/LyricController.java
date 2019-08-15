package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import hello.common.ResponseStatus;
import hello.exception.RestException;

/**
 * @author tu.ta1 on 2019-04-20
 */
@RestController
@RequestMapping("/api/lyric")
public class LyricController {

    @Autowired
    private Environment env;

    @GetMapping(value="", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getLyricByFileName(@RequestParam("file") String file) {
        String folderString = env.getProperty("lyric_folder");

        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(folderString + File.separator + file);
                BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new RestException(ResponseStatus.LYRIC_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }

    @GetMapping(value="/update/offset")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateOffset(@RequestParam("file") String file,
            @RequestParam("offset") Integer offset) {
        String folderString = env.getProperty("lyric_folder");
        changeOffset(folderString + File.separator + file, offset);
        return "Offset updated!";
    }

    private void changeOffset(String filePath, int newOffset) {
        File fileToBeModified = new File(filePath);
        StringBuilder builder = new StringBuilder();
        String content;
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            // Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) {
                if(line.contains("[offset:")) {
                    line = "[offset:" + newOffset + "]";
                }
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }

            content = builder.toString();
            if(!content.contains("[offset:")) {
                content = "[offset:" + newOffset + "]" + System.lineSeparator() + content;
            }

            // Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        } catch (FileNotFoundException e) {
            throw new RestException(ResponseStatus.LYRIC_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
                if(writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
