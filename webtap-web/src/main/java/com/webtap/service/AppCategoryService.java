package com.webtap.service;

import com.webtap.domain.entity.AppCategory;

import java.util.List;

public interface AppCategoryService {
    /**
     * get all app category item
     * @return
     */
    List<AppCategory> getAppCategories(AppCategory category);


    /**
     * get app's categories by orgid
     * @param orgid
     * @return
     */
    List<AppCategory> getAppCategories(Long orgid);

    /**
     * add app category
     * @param appCategory
     * @return
     */
    AppCategory save(AppCategory appCategory);


    AppCategory getAppCategory(Long id);


    void removeCategory(Long id);

}
