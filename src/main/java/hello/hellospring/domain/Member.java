package hello.hellospring.domain;

public class Member {

    private Long id; // 시스템에서 정해주는 id값
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
