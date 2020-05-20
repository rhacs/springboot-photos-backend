package cl.rhacs.springboot.photos.models;

public class PageableResponse {

    // Attributes
    // -----------------------------------------------------------------------------------------

    private int page;

    private int totalPages;

    private int items;

    private long totalItems;

    private Object elements;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new and empty {@link PageableResponse}
     */
    public PageableResponse() {

    }

    /**
     * Creates a new {@link PageableResponse} given the current page, the total
     * amount of pages, the number of items displayes on the page, the total number
     * of items and the objects themselves
     *
     * @param page       the current page
     * @param totalPages the total number of pages
     * @param items      the number of items on the page
     * @param totalItems the total number of items
     * @param elements   the elements
     */
    public PageableResponse(int page, int totalPages, int items, long totalItems, Object elements) {
        this.page = page;
        this.totalPages = totalPages;
        this.items = items;
        this.totalItems = items;
        this.elements = elements;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return the items
     */
    public int getItems() {
        return items;
    }

    /**
     * @return the totalItems
     */
    public long getTotalItems() {
        return totalItems;
    }

    /**
     * @return the elements
     */
    public Object getElements() {
        return elements;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @param items the items to set
     */
    public void setItems(int items) {
        this.items = items;
    }

    /**
     * @param totalItems the totalItems to set
     */
    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(Object elements) {
        this.elements = elements;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "PageableResponse [page=" + page + ", totalPages=" + totalPages + ", items=" + items + ", totalItems="
                + totalItems + ", elements=" + elements + "]";
    }

}
