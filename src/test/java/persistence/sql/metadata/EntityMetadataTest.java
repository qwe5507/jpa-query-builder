package persistence.sql.metadata;

import domain.Person;
import domain.TestDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.dialect.H2.H2Dialect;

import static org.junit.jupiter.api.Assertions.*;

class EntityMetadataTest {
    @DisplayName("Person 객체의 테이블 명을 가져온다.")
    @Test
    void test_getTableName() {
        //Given
        EntityMetadata entityMetadata = new EntityMetadata(Person.class);

        //When & Then
        assertEquals(entityMetadata.getTableName(), "users");
    }

    @DisplayName("Person 객체를 통해 CREATE 컬럼 절을 생성한다.")
    @Test
    void test_getColumnsToCreate() {
        //Given
        EntityMetadata entityMetadata = new EntityMetadata(Person.class);

        //When & Then
        assertEquals(entityMetadata.getColumnsToCreate(new H2Dialect()),
                "id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, nick_name VARCHAR(255), old INTEGER, email VARCHAR(255) NOT NULL"
        );
    }

    @DisplayName("Entity 클래스가 아닐 경우 Exception이 발생한다.")
    @Test
    void When_IsNotEntityClass_Then_ThrowException() {
        //Given

        //When & Then
        assertThrows(
                IllegalArgumentException.class,
                () -> new EntityMetadata(TestDomain.class)
        );
    }

}