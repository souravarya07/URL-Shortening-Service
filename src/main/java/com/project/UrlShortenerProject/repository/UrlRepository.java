package com.project.UrlShortenerProject.repository;

import com.project.UrlShortenerProject.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    public Url findByShortLink(String shortLink);

}
