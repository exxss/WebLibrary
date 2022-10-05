package ru.dob.library.WebLibrary.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги не может быть пустое")
    @Size(min = 2, max = 30, message = "Название книги должно быть от 2 до 30 символов")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Год должен быть больше 0")
    @Column(name = "year")
    private int year;

    @Min(value = 0, message = "Id should be greater than 0")
    @Column(name = "person_id")
    private int personId;

    @NotEmpty(message = "Имя автора не может быть пустое")
    @Size(min = 2, max = 30, message = "Имя автора должно быть от 2 до 30 символов")
    @Column(name = "author")
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
