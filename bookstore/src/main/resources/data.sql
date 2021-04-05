INSERT INTO Author (author_id, author_name) VALUES
    (1, 'Kristin Hannah'),
    (2, 'Matt Haig'),
    (3, 'Glendy Vanderah'),
    (4, 'Hazel Gaynor'),
    (5, 'George Orwell'),
    (6, 'Chris Whitaker');

INSERT INTO Publishing (publishing_id, publishing_name, adress_street, adress_number, adress_zipcode) VALUES
    (1, 'ABC MEDIA CORP.', 'Sant Louis', 234, 3456),
    (2, 'BLUE HOLE INC.', 'Alabbama', 582, 12336),
    (3, 'TEYVAT', 'Di Santos', 788, 4522);

INSERT INTO Book (id, book_name, price, author_id, publishing_id) VALUES
    (1, 'Wish Upon a Lantern', 134.56, 3, 2),
    (2, 'Today, after the night falls', 544.21, 3, 3),
    (3, 'One Upon a Time', 400.32, 5, 1);

    


