package io.member;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-txt.dat";
    private static final String DELIMITER = ",";

    /**
     *  try-with-resources 를 사용하여 자동으로 자원을 정리한다.
     *  try 코드 블록이 끝나면 자동으로 close() 가 호출되어 자원을 정리한다.
     */

    @Override
    public void add(Member member) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
            bw.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] memberData = line.split(DELIMITER);
                members.add(new Member(memberData[0], memberData[1], Integer.valueOf(memberData[2])));
            }
            return members;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
