package bkhn.att.generate_tables;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

/*
 * CHÚ Ý: ĐÂY LÀ BÀI "Tạo ra hệ thống Bảng từ các class Entity trong Hibernate"
 * Bài này chỉ tạo database từ các Entity thôi
 * Nếu có database (dùng flyway) rồi thì không cần run file này nữa!
 * 
 * 
 * Trong tài liệu này tôi sẽ hướng dẫn bạn tạo ra hệ thống các bảng 
 * trên một cơ sở dữ liệu bất kỳ ( Oracle, MySQL, SQL Server, ..)  
 * dựa trên các lớp Entity. Các bảng được tạo ra có đầy đủ các giàng 
 * buộc (Constraints) theo chỉ định trong các lớp Entity. 
 * 
 * Hãy chú ý rằng Hibernate sinh ra để có thể làm việc với mọi cơ sở 
 * dữ liệu. Vì vậy khi chuyển sang sử dụng loại database khác bạn 
 * không phải thay đổi lại code của chương trình. Tuy nhiên bạn cần 
 * phải tạo ra hệ thống các bảng trong database. Hibernate hỗ trợ 
 * bạn tạo ra hệ thống các bảng từ các lớp Entity.
 */
public class SchemaGeneratorDemo {
	public static final String SCRIPT_FILE = "exportScript.sql";

	private static SchemaExport getSchemaExport() {

		SchemaExport export = new SchemaExport();
		// Script file.
		File outputFile = new File(SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();

		System.out.println("Export file: " + outputFilePath);

		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);

		// Không ngừng nếu có lỗi
		export.setHaltOnError(false);
		//
		return export;
	}

	public static void dropDataBase(SchemaExport export, Metadata metadata) {

		// TargetType.DATABASE - Thực thi lệnh vào Databse
		// TargetType.SCRIPT - Ghi ra file Script.
		// TargetType.STDOUT - Ghi thông tin Log ra màn hình Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

		export.drop(targetTypes, metadata);
	}

	public static void createDataBase(SchemaExport export, Metadata metadata) {

		// TargetType.DATABASE - Thực thi lệnh vào Databse
		// TargetType.SCRIPT - Ghi ra file Script.
		// TargetType.STDOUT - Ghi thông tin Log ra màn hình Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

		SchemaExport.Action action = SchemaExport.Action.CREATE;
		//
		export.execute(targetTypes, action, metadata);

		System.out.println("Export OK");

	}

	public static void main(String[] args) {
		// Sử dụng Oracle.
		String configFileName = "hibernate.cfg.xml";

		// Tạo đối tượng ServiceRegistry từ hibernate-xxx.cfg.xml
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
				.configure(configFileName).build();

		// Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

		SchemaExport export = getSchemaExport();

		System.out.println("Drop Database...");
		// Drop Database
		dropDataBase(export, metadata);

		System.out.println("Create Database...");

		// Tạo lại hệ thống bảng
		createDataBase(export, metadata);
	}
}
