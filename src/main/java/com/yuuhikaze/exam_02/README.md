# Exam 02

### Setup

On your MariaDB/MySQL admin console:

```sql
CREATE USER 'CMPEXAMUSER'@'localhost' IDENTIFIED BY 'kHP5Dx2YubIBS778oBqn';
GRANT ALL PRIVILEGES ON mdb_cmp_exam.* TO 'CMPEXAMUSER'@'localhost';
FLUSH PRIVILEGES;
```

That's all!

### Execution

```bash
./gradlew run # Linux, macOS
gradlew.bat run # Windows
```
