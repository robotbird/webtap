package com.webtap.service;

import com.webtap.domain.AppCategory;

import java.util.List;

public interface AppCategoryService {
    /**
     * get all app category item
     * @return
     */
      public List<AppCategory> getAppCategories();

    /**
     * add app category
     * @param appCategory
     * @return
     */
    public AppCategory save(AppCategory appCategory);


    public AppCategory getAppCategory(Long id);


    public void removeCategory(Long id);
}
