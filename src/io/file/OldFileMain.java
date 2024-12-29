package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File 객체
 *      파일과 디렉토리 둘 다 다룬다.
 *      파일/디렉토리 생성: 파일 객체를 생성 후 ,메소드를 통해 생성
 */
public class OldFileMain {

    public static void main(String[] args) throws IOException {

        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("file.exists() = " + file.exists());

        // 파일 생성
        boolean created = file.createNewFile();
        System.out.println("created = " + created);

        // 폴더 생성
        boolean dirCreated = directory.mkdir();
        System.out.println("dirCreated = " + dirCreated);

        // 파일 삭제
//        boolean deleted = file.delete();
//        System.out.println("deleted = " + deleted);

        // 파일인지 확인
        boolean isFile = file.isFile();
        System.out.println("isFile = " + isFile);

        // 디렉토리인지 확인
        boolean isDirectory = directory.isDirectory();
        System.out.println("isDirectory = " + isDirectory);

        // getName(): 파일명/디렉토리명 확인
        String fileName = file.getName();
        System.out.println("fileName = " + fileName);

        // length(): 파일의 크기를 바이트 단위로 반환
        long length = file.length();
        System.out.println("File size: " + length + " bytes");

        // renameTo(File file): 파일명 변경하거나 이동
        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);
        System.out.println("renamed = " + renamed);

        // lastModified(): 마지막으로 수정된 시간을 반환
        long lastModified = newFile.lastModified();
        System.out.println("lastModified = " + new Date(lastModified));


    }

}
