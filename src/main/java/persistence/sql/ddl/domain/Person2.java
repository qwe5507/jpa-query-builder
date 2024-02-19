package persistence.sql.ddl.domain;

@Entity
public class Person2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old")
    private Integer age;
    @Column(nullable = false)
    private String email;
    @Column(name = "nick_name")
    private String name;

}