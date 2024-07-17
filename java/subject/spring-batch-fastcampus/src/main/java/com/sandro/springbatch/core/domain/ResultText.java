package com.sandro.springbatch.core.domain;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicUpdate
@Entity
public class ResultText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;

    protected ResultText() {}

    public ResultText(String text) {
        this.text = text;
    }
}
