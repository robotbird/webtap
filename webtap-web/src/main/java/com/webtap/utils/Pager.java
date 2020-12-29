package com.webtap.utils;

public class Pager
{
    public Pager(int currentPage, int itemsPerPage)
    {
        CurrentPage = currentPage;
        ItemsPerPage = itemsPerPage;

        if (ItemsPerPage == 0)
            ItemsPerPage = 10;

        Newer = CurrentPage - 1;
        ShowNewer = CurrentPage > 1;

        Older = currentPage + 1;
    }

    private void Configure(int total)
    {
        if (total == 0)
            return;

        if (ItemsPerPage == 0)
            ItemsPerPage = 10;

        Total = total;
        int lastItem = CurrentPage * ItemsPerPage;
        ShowOlder = total > lastItem;
        if (CurrentPage < 1 || lastItem > total + ItemsPerPage)
        {
            NotFound = true;
        }
        LastPage = (total % ItemsPerPage) == 0 ? total / ItemsPerPage : (total / ItemsPerPage) + 1;
        if (LastPage == 0) LastPage = 1;
    }

    public int CurrentPage;
    public int ItemsPerPage;
    public int Total;
    public boolean NotFound ;

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    public int getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public boolean isNotFound() {
        return NotFound;
    }

    public void setNotFound(boolean notFound) {
        NotFound = notFound;
    }

    public int getNewer() {
        return Newer;
    }

    public void setNewer(int newer) {
        Newer = newer;
    }

    public boolean isShowNewer() {
        return ShowNewer;
    }

    public void setShowNewer(boolean showNewer) {
        ShowNewer = showNewer;
    }

    public int getOlder() {
        return Older;
    }

    public void setOlder(int older) {
        Older = older;
    }

    public boolean isShowOlder() {
        return ShowOlder;
    }

    public void setShowOlder(boolean showOlder) {
        ShowOlder = showOlder;
    }

    public String getLinkToNewer() {
        return LinkToNewer;
    }

    public void setLinkToNewer(String linkToNewer) {
        LinkToNewer = linkToNewer;
    }

    public String getLinkToOlder() {
        return LinkToOlder;
    }

    public void setLinkToOlder(String linkToOlder) {
        LinkToOlder = linkToOlder;
    }

    public String getRouteValue() {
        return RouteValue;
    }

    public void setRouteValue(String routeValue) {
        RouteValue = routeValue;
    }

    public int getLastPage() {
        return LastPage;
    }

    public void setLastPage(int lastPage) {
        LastPage = lastPage;
    }

    public int Newer;
    public boolean ShowNewer ;

    public int Older ;
    public boolean ShowOlder ;

    public String LinkToNewer;
    public String LinkToOlder ;

    public String RouteValue ;
    public int LastPage;

}