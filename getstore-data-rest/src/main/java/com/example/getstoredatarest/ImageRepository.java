package com.example.getstoredatarest;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {

  List<Image> findByImageFileName(@Param("image") String image);

}