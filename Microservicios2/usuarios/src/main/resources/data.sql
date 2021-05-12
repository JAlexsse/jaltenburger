INSERT INTO usuarios (
    username, 
    password, 
    enabled, 
    name, 
    lastname, 
    email
) VALUES 
    ('Maria123', '123123', 1, 'Maria', 'Castro', 'maria@email.com'),
    ('Hector52', '123123', 1, 'Hector', 'Hugo', 'hector@email.com');

INSERT INTO roles (name) VALUES 
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2);