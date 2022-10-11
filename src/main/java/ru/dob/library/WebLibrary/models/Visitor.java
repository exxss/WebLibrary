package ru.dob.library.WebLibrary.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "person")
public class Visitor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Размер имени должен быть от 2 до 30 символов")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1920, message = "Дата рождения не может быть меньше 1920 года")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "number")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Введите номер в формате: \"7 ххх ххх хх хх\"")
    private String number;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Visitor() {

    }

    public Visitor(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
