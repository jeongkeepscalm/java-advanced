package io.member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataStream
 *      데이터 타입을 그대로 사용하여 파일에 데이터 저장 / 불러오기
 *      구분자 사용 필요 x
 *      모든 데이터를 문자로 저장할 때 보다 저장 용량도 더 최적화 할 수 있다.
 */
public class DataMemberRepository implements MemberRepository {
    private static final String FILE_PATH = "temp/members-data.dat";
    @Override
    public void add(Member member) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))) {
            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) {
            while (dis.available() > 0) {
                members.add(new Member(dis.readUTF(), dis.readUTF(), dis.readInt()));
            }
            return members;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
