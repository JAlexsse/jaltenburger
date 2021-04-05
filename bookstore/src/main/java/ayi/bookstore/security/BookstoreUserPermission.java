package ayi.bookstore.security;

public enum BookstoreUserPermission {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    AUTHOR_READ("author:read"),
    AUTHOR_WRITE("author:write"),
    PUBLISHING_READ("publishing:read"),
    PUBLISHING_WRITE("publishing:write");

    private final String permission;

    BookstoreUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
    
}
