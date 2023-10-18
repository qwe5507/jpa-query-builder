package persistence.sql.ddl.generator;

import persistence.sql.ddl.dialect.ColumnType;
import persistence.sql.ddl.schema.EntityMappingMeta;

public class CreateDDLQueryGenerator {

    private final ColumnType columnType;

    public static final String CREATE_TABLE_FORMAT = "CREATE TABLE %s (%s);";

    public CreateDDLQueryGenerator(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String create(Class<?> entityClazz) {
        final EntityMappingMeta entityMappingMeta = EntityMappingMeta.of(entityClazz, columnType);

        return String.format(CREATE_TABLE_FORMAT, entityMappingMeta.tableClause(), entityMappingMeta.fieldClause());
    }
}