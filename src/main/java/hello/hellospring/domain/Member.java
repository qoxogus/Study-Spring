package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템에서 정해주는 id값 (PRIMARY KEY)

//    @Column(name = "username") // db컬럼 이름이 username이라면 이렇게 해주면 매핑이 된다.
    private String name; //회원가입할때 적는 이름


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
