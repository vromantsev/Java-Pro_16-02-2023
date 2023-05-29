package ua.hillel.hw.task25;

import ua.hillel.hw.task25.connection.DatabaseConnection;
import ua.hillel.hw.task25.connection.DatabaseConnectionManager;
import ua.hillel.hw.task25.dao.GenericDao;
import ua.hillel.hw.task25.dao.LessonDao;
import ua.hillel.hw.task25.entity.Lesson;
import ua.hillel.hw.task25.utils.CustomJdbcUtils;

public class JdbcHwDemo {

    private static final String SQL_SCRIPTS = "sql/init-db.sql";

    public static void main(String[] args) {
        CustomJdbcUtils.initSqlScript(SQL_SCRIPTS, CustomJdbcUtils.initDataSource());

        GenericDao<Lesson, Long> lessonDao = new LessonDao(new DatabaseConnectionManager(CustomJdbcUtils.initDataSource()));

        lessonDao.findAll().forEach(System.out::println);
    }
}
