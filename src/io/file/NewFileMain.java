package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Files
 *      File 상위 호환
 *      파일이나, 디렉토리의 경로는 Path 클래스를 사용
 */
public class NewFileMain {

    public static void main(String[] args) throws IOException {

        Path filePath = Path.of("temp/example.txt");
        Path directoryPath = Path.of("temp/exampleDir");

        // exists(): 파일/디렉토리 존재 여부 확인
        boolean exists = Files.exists(filePath);
        System.out.println("exists = " + exists);
        
        // createFile(): 새 파일 생성
        try {
            Files.createFile(filePath);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(filePath + " File already exists");
        }

        // createDirectory(): 새 디렉토리 생성
        try {
            Files.createDirectory(directoryPath);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(directoryPath + " Directory already exists");
        }

        // delete(): 파일/디렉토리 삭제
//        Files.delete(filePath);
//        System.out.println("File deleted");

        // isRegularFile(): 일반 파일인지 확인
        System.out.println("Is regular file: " + Files.isRegularFile(filePath));

        // isDirectory(): 디렉토리인지 확인
        System.out.println("Is directory: " + Files.isDirectory(directoryPath));

        // getFileName(): 파일이나 디렉토리의 이름을 반환
        System.out.println("File name: " + filePath.getFileName());

        // size(): 파일의 크기를 바이트 단위로 반환
        System.out.println("File size: " + Files.size(filePath) + " bytes");

        // move(): 파일명 변경 / 이동
        Path newFilePath = Paths.get("temp/newExample.txt");
        Files.move(filePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved/renamed");

        // getLastModifiedTime(): 마지막으로 수정된 시간을 반환
        System.out.println("Last modified: " + Files.getLastModifiedTime(newFilePath));

        // readAttributes(): 파일 기본 속성들 읽기
        BasicFileAttributes attrs = Files.readAttributes(newFilePath, BasicFileAttributes.class);
        System.out.println("===== Attributes =====");
        System.out.println("Creation time: " + attrs.creationTime());
        System.out.println("Is directory: " + attrs.isDirectory());
        System.out.println("Is regular file: " + attrs.isRegularFile());
        System.out.println("Is symbolic link: " + attrs.isSymbolicLink());
        System.out.println("Size: " + attrs.size());
    }

}
