package io.member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ObjectOutputStream
 *      인스턴스를 직렬화해서 byte 로 변경
 *
 * 현재는 객체 직렬화를 잘 사용하지 않는다.
 * 객체 직려화의 한계
 *      버전 관리 어려움
 *      플랫폼 종속성
 *      성능 이슈
 *      유연성 부족
 *      크기 효율성
 * 객체 직렬화 대안
 *      xml: 복잡, 무거움.
 *         <member>
 *             <id>id1</id>
 *             <name>name1</name>
 *             <age>20</age>
 *         </member>
 *      json
 *           {"member": { "id": "id1", "name": "name1", "age": 20 } }
 */
public class ObjectMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-obj.dat";

    @Override
    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            // members 컬렉션 직렬화해서 byte 로 변경
            oos.writeObject(members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            // 역직렬화: Object 반환
            Object findObject = ois.readObject();
            return (List<Member>) findObject;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
