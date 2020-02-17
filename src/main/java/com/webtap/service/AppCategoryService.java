package com.webtap.service;

import com.webtap.domain.AppCategory;

import java.util.List;

public interface AppCategoryService {
    /**
     * get all app category item
     * @return
     */
      public List<AppCategory> getAppCategories();
}
