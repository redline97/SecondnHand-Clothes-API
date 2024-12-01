-- liquibase formatted sql

-- changeset publisher_insert:20241130-10
INSERT INTO public.publisher ( username, "password", full_name, address) VALUES( 'reddy', '$2a$10$j.X2oya2cvw9ZWKOhsOMIORNDls/xc/dCTMYCU3UegRSCHLqs2bUu', 'redi ramaj', 'tirana, albania');

-- changeset garment_insert:20241130-20
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Shirt', 'M', 'A stylish cotton shirt', 29.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Pants', 'L', 'Comfortable denim jeans', 49.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Jacket', 'XL', 'A warm winter jacket', 89.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'T-Shirt', 'S', 'A casual graphic tee', 19.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Sweater', 'M', 'A cozy wool sweater', 39.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Dress', 'M', 'A sleek black dress', 59.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Skirt', 'S', 'A flowy floral skirt', 34.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES('reddy', 'Blouse', 'M', 'A formal white blouse', 44.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Shoes', '42', 'Comfortable running shoes', 79.99);
INSERT INTO public.garment ( publisher_username, "type", "size", description, price) VALUES( 'reddy', 'Shorts', 'XXL', 'Lightweight summer shorts', 24.99);
