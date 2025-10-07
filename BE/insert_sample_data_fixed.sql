-- =============================================
-- SQL INSERT SCRIPT FOR CARRY DATABASE
-- Generated based on Entity relationships
-- Microsoft SQL Server (T-SQL) Version
-- =============================================

USE carry;

-- =============================================
-- 1. BẢNG MEMBERS (Không phụ thuộc bảng nào)
-- =============================================
INSERT INTO members (username, address, email, phone, city, password, role, status, avatar_url, created_at)
VALUES
    ('admin', N'123 Đường Nguyễn Huệ, Quận 1', 'admin@carry.com', '0901234567', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'ADMIN', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-01-01 08:00:00' AS DATETIME)),
    ('johndoe', N'456 Đường Lê Lợi, Quận 1', 'john.doe@gmail.com', '0912345678', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-01-15 10:30:00' AS DATETIME)),
    ('janesmit', N'789 Phố Hoàn Kiếm, Quận Ba Đình', 'jane.smith@gmail.com', '0923456789', N'Hà Nội', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-02-01 14:20:00' AS DATETIME)),
    ('bobseller', N'321 Đường Trần Phú, Quận Hải Châu', 'bob.seller@gmail.com', '0934567890', N'Đà Nẵng', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-02-10 09:15:00' AS DATETIME)),
    ('alicebuyer', N'654 Đường Hai Bà Trưng, Quận 3', 'alice.buyer@gmail.com', '0945678901', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-05 16:45:00' AS DATETIME)),
    ('minh_nguyen', N'123 Đường Nguyễn Thái Học, Quận Ba Đình', 'minh.nguyen@gmail.com', '0956789012', N'Hà Nội', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-10 11:00:00' AS DATETIME)),
    ('trang_le', N'456 Đường Lý Thường Kiệt, Quận 10', 'trang.le@gmail.com', '0967890123', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-12 09:30:00' AS DATETIME)),
    ('duc_pham', N'789 Đường Lê Duẩn, Quận Hải Châu', 'duc.pham@gmail.com', '0978901234', N'Đà Nẵng', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-15 14:45:00' AS DATETIME)),
    ('linh_vo', N'321 Đường Hoàng Văn Thụ, Quận Tân Bình', 'linh.vo@gmail.com', '0989012345', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-18 16:20:00' AS DATETIME)),
    ('huy_tran', N'654 Đường Điện Biên Phủ, Quận Bình Thạnh', 'huy.tran@gmail.com', '0990123456', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-20 10:15:00' AS DATETIME)),
    ('thao_hoang', N'987 Đường Ngô Quyền, Quận Sơn Trà', 'thao.hoang@gmail.com', '0901234568', N'Đà Nẵng', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-22 13:40:00' AS DATETIME)),
    ('nam_bui', N'147 Đường Trần Hưng Đạo, Quận Hoàn Kiếm', 'nam.bui@gmail.com', '0912345679', N'Hà Nội', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-25 15:10:00' AS DATETIME)),
    ('mai_do', N'258 Đường Võ Thị Sáu, Quận 3', 'mai.do@gmail.com', '0923456780', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-28 12:25:00' AS DATETIME)),
    ('long_nguyen', N'369 Đường Phan Châu Trinh, Quận Thanh Khê', 'long.nguyen@gmail.com', '0934567891', N'Đà Nẵng', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-04-01 08:50:00' AS DATETIME)),
    ('ha_pham', N'741 Đường Cách Mạng Tháng 8, Quận 10', 'ha.pham@gmail.com', '0945678902', N'Thành phố Hồ Chí Minh', '$2a$10$encrypted_password_here', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-04-03 17:35:00' AS DATETIME));

-- =============================================
-- 2. BẢNG MEMBERSHIP_PLANS (Không phụ thuộc bảng nào)
-- =============================================
INSERT INTO membership_plans (name, price, duration, max_posts, priority)
VALUES
    ('Free', 0.00, 30, 5, 'LOW'),
    ('Basic', 99000.00, 30, 20, 'MEDIUM'),
    ('Premium', 299000.00, 30, 100, 'HIGH'),
    ('VIP', 599000.00, 30, 999, 'HIGHEST'),
    ('Student', 49000.00, 30, 10, 'MEDIUM'),
    ('Business', 499000.00, 30, 500, 'HIGH');

-- =============================================
-- 3. BẢNG VEHICLE (Không phụ thuộc bảng nào)
-- =============================================
INSERT INTO vehicle (name, brand, model, mileage, condition, register_year, battery_capacity, origin)
VALUES
    ('Honda SH 150i', 'Honda', 'SH 150i', 5000, 'Excellent', '2022', '60V 20Ah', N'Việt Nam'),
    ('Yamaha Janus', 'Yamaha', 'Janus', 8000, 'Good', '2021', '48V 20Ah', N'Việt Nam'),
    ('VinFast Klara A2', 'VinFast', 'Klara A2', 3000, 'Excellent', '2023', '60V 28Ah', N'Việt Nam'),
    ('Pega NewTech', 'Pega', 'NewTech', 12000, 'Fair', '2020', '48V 20Ah', N'Việt Nam'),
    ('Yadea G5', 'Yadea', 'G5', 6500, 'Good', '2022', '60V 32Ah', N'Trung Quốc'),
    ('Honda Vision 110', 'Honda', 'Vision 110', 7500, 'Good', '2021', '12V 7Ah', N'Việt Nam'),
    ('Yamaha Grande', 'Yamaha', 'Grande', 4200, 'Excellent', '2023', '12V 9Ah', N'Việt Nam'),
    ('Piaggio Vespa Primavera', 'Piaggio', 'Vespa Primavera', 2800, 'Excellent', '2023', '12V 10Ah', N'Ý'),
    ('VinFast Theon S', 'VinFast', 'Theon S', 1500, 'New', '2024', '72V 28Ah', N'Việt Nam'),
    ('Dibao Luxury', 'Dibao', 'Luxury', 9800, 'Good', '2020', '48V 20Ah', N'Việt Nam'),
    ('Honda PCX 160', 'Honda', 'PCX 160', 3500, 'Excellent', '2022', '12V 8Ah', N'Việt Nam'),
    ('Suzuki Raider R150', 'Suzuki', 'Raider R150', 15000, 'Fair', '2019', '12V 6Ah', N'Nhật Bản'),
    ('VinFast Klara S', 'VinFast', 'Klara S', 2100, 'Excellent', '2023', '60V 35Ah', N'Việt Nam'),
    ('Pega Cap A', 'Pega', 'Cap A', 11000, 'Good', '2020', '48V 22Ah', N'Việt Nam'),
    ('Yadea Xmen Neo', 'Yadea', 'Xmen Neo', 5800, 'Good', '2022', '72V 20Ah', N'Trung Quốc'),
    ('Honda Lead 125', 'Honda', 'Lead 125', 6200, 'Good', '2021', '12V 7Ah', N'Việt Nam'),
    ('Yamaha FreeGo 125', 'Yamaha', 'FreeGo 125', 4800, 'Excellent', '2022', '12V 8Ah', N'Việt Nam'),
    ('VinFast Ludo', 'VinFast', 'Ludo', 800, 'New', '2024', '48V 26Ah', N'Việt Nam'),
    ('Pega Xmax', 'Pega', 'Xmax', 13500, 'Fair', '2019', '60V 20Ah', N'Việt Nam'),
    ('Yadea Like 200', 'Yadea', 'Like 200', 7200, 'Good', '2021', '72V 32Ah', N'Trung Quốc');

-- =============================================
-- 4. BẢNG BATTERY (Không phụ thuộc bảng nào)
-- =============================================
INSERT INTO battery (name, brand, year_at, voltage_v, capacity_ah, condition, origin)
VALUES
    ('Lithium Battery LG', 'LG Chem', '2023', '60V', 28, 'New', N'Hàn Quốc'),
    ('Panasonic Battery', 'Panasonic', '2022', '48V', 20, 'Good', N'Nhật Bản'),
    ('Samsung SDI Battery', 'Samsung', '2023', '60V', 32, 'New', N'Hàn Quốc'),
    ('CATL Battery', 'CATL', '2021', '72V', 40, 'Good', N'Trung Quốc'),
    ('BYD Battery', 'BYD', '2022', '60V', 20, 'Fair', N'Trung Quốc'),
    ('VinFast Original Battery', 'VinFast', '2024', '72V', 28, 'New', N'Việt Nam'),
    ('Bosch Lithium Ion', 'Bosch', '2023', '48V', 25, 'Excellent', N'Đức'),
    ('Tesla Model Battery', 'Tesla', '2022', '60V', 35, 'Good', N'Mỹ'),
    ('Pega High Performance', 'Pega', '2021', '60V', 30, 'Good', N'Việt Nam'),
    ('Yadea Long Range', 'Yadea', '2023', '72V', 45, 'Excellent', N'Trung Quốc'),
    ('Honda Genuine Battery', 'Honda', '2022', '12V', 9, 'Good', N'Việt Nam'),
    ('Yamaha Original Battery', 'Yamaha', '2023', '12V', 8, 'Excellent', N'Việt Nam'),
    ('Lithium Pro Max', 'Generic', '2021', '48V', 22, 'Fair', N'Trung Quốc'),
    ('Super Power Battery', 'SuperPower', '2024', '60V', 38, 'New', N'Hàn Quốc'),
    ('EcoTech Green Battery', 'EcoTech', '2022', '72V', 42, 'Good', N'Nhật Bản');

-- =============================================
-- 5. BẢNG PRODUCTS (Phụ thuộc: members, vehicle, battery)
-- =============================================
INSERT INTO products (member_id, vehicle_id, battery_id, product_type, name, description, status, created_at)
VALUES
    (2, 1, NULL, 'VEHICLE', 'Honda SH 150i 2022', N'Honda SH 150i đời 2022, màu đỏ, đi 5000km, xe zin chưa té ngã', 'ACTIVE', CAST('2024-03-10 10:00:00' AS DATETIME)),
    (3, 2, NULL, 'VEHICLE', 'Yamaha Janus 2021', N'Yamaha Janus đời 2021, màu xanh, xe đẹp máy ngon', 'ACTIVE', CAST('2024-03-12 11:30:00' AS DATETIME)),
    (4, NULL, 1, 'BATTERY', 'Pin LG 60V 28Ah', N'Pin Lithium LG mới 100%, bảo hành 2 năm', 'ACTIVE', CAST('2024-03-15 14:20:00' AS DATETIME)),
    (4, 3, NULL, 'VEHICLE', 'VinFast Klara A2', N'VinFast Klara A2 2023, màu trắng, như mới', 'ACTIVE', CAST('2024-03-18 09:45:00' AS DATETIME)),
    (2, NULL, 2, 'BATTERY', 'Pin Panasonic 48V', N'Pin Panasonic đã dùng 1 năm, còn tốt', 'ACTIVE', CAST('2024-03-20 16:10:00' AS DATETIME)),
    (6, 6, NULL, 'VEHICLE', 'Honda Vision 110', N'Honda Vision 110 màu xám bạc, đi 7500km, máy êm ru', 'ACTIVE', CAST('2024-03-22 08:30:00' AS DATETIME)),
    (7, 7, NULL, 'VEHICLE', 'Yamaha Grande 2023', N'Yamaha Grande mới cứng, màu đen, chỉ đi 4200km', 'ACTIVE', CAST('2024-03-24 10:15:00' AS DATETIME)),
    (8, NULL, 3, 'BATTERY', 'Pin Samsung SDI 60V', N'Pin Samsung SDI chất lượng cao, mới 100%', 'ACTIVE', CAST('2024-03-25 13:45:00' AS DATETIME)),
    (9, 8, NULL, 'VEHICLE', 'Vespa Primavera', N'Vespa Primavera 2023 màu xanh mint, xe Ý nhập khẩu', 'ACTIVE', CAST('2024-03-27 15:20:00' AS DATETIME)),
    (10, 9, NULL, 'VEHICLE', 'VinFast Theon S', N'VinFast Theon S mới 100%, chưa lăn bánh', 'ACTIVE', CAST('2024-03-28 11:00:00' AS DATETIME)),
    (11, NULL, 4, 'BATTERY', 'Pin CATL 72V 40Ah', N'Pin CATL dung lượng lớn, phù hợp xe cao cấp', 'ACTIVE', CAST('2024-03-29 14:30:00' AS DATETIME)),
    (12, 11, NULL, 'VEHICLE', 'Honda PCX 160 2022', N'Honda PCX 160 màu trắng đen, sporty và tiết kiệm', 'ACTIVE', CAST('2024-03-30 09:20:00' AS DATETIME)),
    (13, 13, NULL, 'VEHICLE', 'VinFast Klara S', N'VinFast Klara S phiên bản cao cấp, pin lớn', 'ACTIVE', CAST('2024-04-01 16:40:00' AS DATETIME)),
    (14, NULL, 6, 'BATTERY', 'Pin VinFast 72V', N'Pin VinFast chính hãng, bảo hành dài hạn', 'ACTIVE', CAST('2024-04-02 12:15:00' AS DATETIME)),
    (15, 18, NULL, 'VEHICLE', 'VinFast Ludo 2024', N'VinFast Ludo mới ra mắt, thiết kế trẻ trung', 'ACTIVE', CAST('2024-04-03 10:50:00' AS DATETIME)),
    (6, NULL, 7, 'BATTERY', 'Pin Bosch 48V', N'Pin Bosch Đức, chất lượng châu Âu', 'ACTIVE', CAST('2024-04-04 13:25:00' AS DATETIME)),
    (7, 16, NULL, 'VEHICLE', 'Honda Lead 125', N'Honda Lead 125 màu đỏ, xe gia đình sử dụng', 'ACTIVE', CAST('2024-04-05 11:10:00' AS DATETIME)),
    (8, 17, NULL, 'VEHICLE', 'Yamaha FreeGo 125', N'Yamaha FreeGo 125 màu xanh dương, trẻ trung', 'ACTIVE', CAST('2024-04-06 14:45:00' AS DATETIME)),
    (9, NULL, 10, 'BATTERY', 'Pin Yadea 72V', N'Pin Yadea tầm xa, dung lượng 45Ah', 'ACTIVE', CAST('2024-04-07 09:35:00' AS DATETIME)),
    (10, 20, NULL, 'VEHICLE', 'Yadea Like 200', N'Yadea Like 200 thiết kế Vespa, giá tốt', 'ACTIVE', CAST('2024-04-08 15:55:00' AS DATETIME));

-- =============================================
-- 6. BẢNG POSTS (Phụ thuộc: products, members)
-- =============================================
INSERT INTO posts (product_id, seller_id, title, description, status, price, created_at)
VALUES
    (1, 2, N'Bán Honda SH 150i 2022 giá tốt', N'Honda SH 150i đời 2022, màu đỏ, đi 5000km. Xe zin chưa té ngã, máy êm ru. Giá thương lượng cho khách thiện chí.', 'ACTIVE', 65000000.00, CAST('2024-03-10 10:05:00' AS DATETIME)),
    (2, 3, N'Yamaha Janus 2021 cần bán gấp', N'Yamaha Janus đời 2021, màu xanh. Xe đẹp máy ngon, phanh CBS an toàn. Cần bán gấp vì chuyển công tác.', 'ACTIVE', 28000000.00, CAST('2024-03-12 11:35:00' AS DATETIME)),
    (3, 4, N'Pin LG chính hãng giá rẻ', N'Pin Lithium LG 60V 28Ah mới 100%, bảo hành chính hãng 2 năm. Phù hợp với các dòng xe SH, Klara.', 'ACTIVE', 8500000.00, CAST('2024-03-15 14:25:00' AS DATETIME)),
    (4, 4, N'VinFast Klara A2 2023 như mới', N'VinFast Klara A2 2023, màu trắng, đi 3000km. Xe như mới, bảo hành còn dài.', 'ACTIVE', 32000000.00, CAST('2024-03-18 09:50:00' AS DATETIME)),
    (5, 2, N'Pin Panasonic 48V giá sinh viên', N'Pin Panasonic 48V 20Ah đã dùng 1 năm, còn chạy tốt 40-50km/sạc. Giá rẻ cho sinh viên.', 'ACTIVE', 3500000.00, CAST('2024-03-20 16:15:00' AS DATETIME)),
    (6, 6, N'Honda Vision 110 - Xe gia đình', N'Honda Vision 110 đời 2021, màu xám bạc. Xe gia đình sử dụng, bảo dưỡng định kỳ. Giấy tờ đầy đủ.', 'ACTIVE', 22000000.00, CAST('2024-03-22 08:35:00' AS DATETIME)),
    (7, 7, N'Yamaha Grande 2023 mới cứng', N'Yamaha Grande 2023 màu đen, chỉ đi 4200km. Xe mới cứng, bao test máy. Bán vì cần tiền gấp.', 'ACTIVE', 31000000.00, CAST('2024-03-24 10:20:00' AS DATETIME)),
    (8, 8, N'Pin Samsung SDI cao cấp', N'Pin Samsung SDI 60V 32Ah mới 100%. Công nghệ Hàn Quốc, tuổi thọ cao, sạc nhanh.', 'ACTIVE', 12000000.00, CAST('2024-03-25 13:50:00' AS DATETIME)),
    (9, 9, N'Vespa Primavera - Xe Ý nhập khẩu', N'Piaggio Vespa Primavera 2023 màu xanh mint. Xe Ý nhập khẩu chính hãng, thiết kế sang trọng.', 'ACTIVE', 85000000.00, CAST('2024-03-27 15:25:00' AS DATETIME)),
    (10, 10, N'VinFast Theon S 2024 - Chưa lăn bánh', N'VinFast Theon S 2024 mới 100%, chưa lăn bánh. Xe điện cao cấp nhất của VinFast.', 'ACTIVE', 69000000.00, CAST('2024-03-28 11:05:00' AS DATETIME)),
    (11, 11, N'Pin CATL 72V 40Ah cao cấp', N'Pin CATL dung lượng lớn, phù hợp xe cao cấp', 'ACTIVE', 15000000.00, CAST('2024-03-29 14:30:00' AS DATETIME)),
    (12, 12, N'Honda PCX 160 2022 sporty', N'Honda PCX 160 màu trắng đen, sporty và tiết kiệm', 'ACTIVE', 45000000.00, CAST('2024-03-30 09:20:00' AS DATETIME)),
    (13, 13, N'VinFast Klara S cao cấp', N'VinFast Klara S phiên bản cao cấp, pin lớn', 'ACTIVE', 38000000.00, CAST('2024-04-01 16:40:00' AS DATETIME)),
    (14, 14, N'Pin VinFast 72V chính hãng', N'Pin VinFast chính hãng, bảo hành dài hạn', 'ACTIVE', 18000000.00, CAST('2024-04-02 12:15:00' AS DATETIME)),
    (15, 15, N'VinFast Ludo 2024 mới', N'VinFast Ludo mới ra mắt, thiết kế trẻ trung', 'ACTIVE', 29000000.00, CAST('2024-04-03 10:50:00' AS DATETIME)),
    (16, 6, N'Pin Bosch 48V chất lượng Đức', N'Pin Bosch Đức, chất lượng châu Âu', 'ACTIVE', 9500000.00, CAST('2024-04-04 13:25:00' AS DATETIME)),
    (17, 7, N'Honda Lead 125 gia đình', N'Honda Lead 125 màu đỏ, xe gia đình sử dụng', 'ACTIVE', 25000000.00, CAST('2024-04-05 11:10:00' AS DATETIME)),
    (18, 8, N'Yamaha FreeGo 125 trẻ trung', N'Yamaha FreeGo 125 màu xanh dương, trẻ trung', 'ACTIVE', 33000000.00, CAST('2024-04-06 14:45:00' AS DATETIME)),
    (19, 9, N'Pin Yadea 72V tầm xa', N'Pin Yadea tầm xa, dung lượng 45Ah', 'ACTIVE', 11000000.00, CAST('2024-04-07 09:35:00' AS DATETIME)),
    (20, 10, N'Yadea Like 200 thiết kế Vespa', N'Yadea Like 200 thiết kế Vespa, giá tốt', 'ACTIVE', 24000000.00, CAST('2024-04-08 15:55:00' AS DATETIME));

-- =============================================
-- 7. BẢNG POST_IMAGES (Phụ thuộc: posts)
-- =============================================
INSERT INTO post_images (post_id, image_url)
VALUES
    -- Honda SH 150i 2022
    (1, 'https://i.pravatar.cc/300'),
    (1, 'https://i.pravatar.cc/300'),
    (1, 'https://i.pravatar.cc/300'),
    -- Yamaha Janus 2021
    (2, 'https://i.pravatar.cc/300'),
    (2, 'https://i.pravatar.cc/300'),
    -- Pin LG
    (3, 'https://i.pravatar.cc/300'),
    -- VinFast Klara A2
    (4, 'https://i.pravatar.cc/300'),
    (4, 'https://i.pravatar.cc/300'),
    -- Pin Panasonic
    (5, 'https://i.pravatar.cc/300'),
    -- Honda Vision 110
    (6, 'https://i.pravatar.cc/300'),
    (6, 'https://i.pravatar.cc/300'),
    -- Yamaha Grande
    (7, 'https://i.pravatar.cc/300'),
    (7, 'https://i.pravatar.cc/300'),
    -- Pin Samsung
    (8, 'https://i.pravatar.cc/300'),
    -- Vespa Primavera
    (9, 'https://i.pravatar.cc/300'),
    (9, 'https://i.pravatar.cc/300'),
    -- VinFast Theon S
    (10, 'https://i.pravatar.cc/300'),
    (10, 'https://i.pravatar.cc/300'),
    -- Pin CATL
    (11, 'https://i.pravatar.cc/300'),
    -- Honda PCX 160
    (12, 'https://i.pravatar.cc/300'),
    (12, 'https://i.pravatar.cc/300'),
    -- VinFast Klara S
    (13, 'https://i.pravatar.cc/300'),
    (13, 'https://i.pravatar.cc/300'),
    -- Pin VinFast
    (14, 'https://i.pravatar.cc/300'),
    -- VinFast Ludo
    (15, 'https://i.pravatar.cc/300'),
    (15, 'https://i.pravatar.cc/300'),
    -- Pin Bosch
    (16, 'https://i.pravatar.cc/300'),
    -- Honda Lead 125
    (17, 'https://i.pravatar.cc/300'),
    (17, 'https://i.pravatar.cc/300'),
    -- Yamaha FreeGo 125
    (18, 'https://i.pravatar.cc/300'),
    (18, 'https://i.pravatar.cc/300'),
    -- Pin Yadea
    (19, 'https://i.pravatar.cc/300'),
    -- Yadea Like 200
    (20, 'https://i.pravatar.cc/300'),
    (20, 'https://i.pravatar.cc/300');

-- =============================================
-- 8. BẢNG MEMBER_PLAN_USAGE (Phụ thuộc: members, membership_plans)
-- =============================================
INSERT INTO member_plan_usage (member_id, plan_id, start_date, end_date, used_posts, status)
VALUES
    (2, 2, CAST('2024-03-01 00:00:00' AS DATETIME), CAST('2024-03-31 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (3, 1, CAST('2024-02-01 00:00:00' AS DATETIME), CAST('2024-03-02 23:59:59' AS DATETIME), 1, 'ACTIVE'),
    (4, 3, CAST('2024-03-01 00:00:00' AS DATETIME), CAST('2024-03-31 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (5, 1, CAST('2024-03-05 00:00:00' AS DATETIME), CAST('2024-04-04 23:59:59' AS DATETIME), 0, 'ACTIVE'),
    (6, 2, CAST('2024-03-15 00:00:00' AS DATETIME), CAST('2024-04-14 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (7, 5, CAST('2024-03-20 00:00:00' AS DATETIME), CAST('2024-04-19 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (8, 3, CAST('2024-03-22 00:00:00' AS DATETIME), CAST('2024-04-21 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (9, 4, CAST('2024-03-25 00:00:00' AS DATETIME), CAST('2024-04-24 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (10, 6, CAST('2024-03-28 00:00:00' AS DATETIME), CAST('2024-04-27 23:59:59' AS DATETIME), 2, 'ACTIVE'),
    (11, 2, CAST('2024-03-29 00:00:00' AS DATETIME), CAST('2024-04-28 23:59:59' AS DATETIME), 1, 'ACTIVE'),
    (12, 3, CAST('2024-03-30 00:00:00' AS DATETIME), CAST('2024-04-29 23:59:59' AS DATETIME), 1, 'ACTIVE'),
    (13, 4, CAST('2024-04-01 00:00:00' AS DATETIME), CAST('2024-04-30 23:59:59' AS DATETIME), 1, 'ACTIVE'),
    (14, 1, CAST('2024-04-02 00:00:00' AS DATETIME), CAST('2024-05-01 23:59:59' AS DATETIME), 1, 'ACTIVE'),
    (15, 5, CAST('2024-04-03 00:00:00' AS DATETIME), CAST('2024-05-02 23:59:59' AS DATETIME), 1, 'ACTIVE');

-- =============================================
-- 9. BẢNG FAVORITES (Phụ thuộc: members, posts)
-- =============================================
INSERT INTO favorites (member_id, post_id)
VALUES
    (5, 1),   -- Alice thích Honda SH
    (5, 4),   -- Alice thích VinFast Klara
    (3, 3),   -- Jane thích Pin LG
    (2, 4),   -- John thích VinFast Klara
    (6, 9),   -- Minh thích Vespa Primavera
    (7, 10),  -- Trang thích VinFast Theon S
    (8, 12),  -- Đức thích Honda PCX 160
    (9, 13),  -- Linh thích VinFast Klara S
    (10, 6),  -- Huy thích Honda Vision 110
    (11, 7),  -- Thảo thích Yamaha Grande
    (12, 8),  -- Nam thích Pin Samsung
    (13, 11), -- Mai thích Pin CATL
    (14, 15), -- Long thích VinFast Ludo
    (15, 18), -- Hà thích Yamaha FreeGo
    (2, 20),  -- John thích Yadea Like 200
    (3, 19),  -- Jane thích Pin Yadea
    (4, 17),  -- Bob thích Honda Lead 125
    (6, 16),  -- Minh thích Pin Bosch
    (7, 14),  -- Trang thích Pin VinFast
    (8, 5);   -- Đức thích Pin Panasonic

-- =============================================
-- 10. BẢNG COMMENTS (Phụ thuộc: posts, members)
-- =============================================
INSERT INTO comments (post_id, member_id, rating, comment, status, created_at)
VALUES
    (1, 5, 5, N'Xe đẹp quá! Cho mình xem xe được không ạ?', 'ACTIVE', '2024-03-11 14:30:00'),
    (1, 3, 4, N'Giá có thương lượng không bạn?', 'ACTIVE', '2024-03-11 16:45:00'),
    (2, 2, 5, N'Xe còn không bạn? Mình có nhu cầu mua.', 'ACTIVE', '2024-03-13 10:20:00'),
    (3, 5, 5, N'Pin này dùng cho xe gì được ạ?', 'ACTIVE', '2024-03-16 09:15:00'),
    (4, 5, 5, N'Xe đẹp lắm! Mình muốn mua.', 'ACTIVE', '2024-03-19 11:00:00'),
    (6, 7, 4, N'Honda Vision này có bảo hành không ạ?', 'ACTIVE', '2024-03-23 10:20:00'),
    (7, 8, 5, N'Yamaha Grande giá này quá hời! Xe còn không?', 'ACTIVE', '2024-03-24 15:30:00'),
    (8, 6, 5, N'Pin Samsung này chất lượng thế nào ạ?', 'ACTIVE', '2024-03-26 09:45:00'),
    (9, 10, 5, N'Vespa Primavera đẹp quá! Có thể xem xe không?', 'ACTIVE', '2024-03-28 14:15:00'),
    (10, 11, 4, N'VinFast Theon S này có sạc nhanh không?', 'ACTIVE', '2024-03-29 11:30:00'),
    (11, 12, 5, N'Pin CATL này chạy được bao xa vậy ạ?', 'ACTIVE', '2024-03-30 16:20:00'),
    (12, 13, 4, N'Honda PCX 160 có tiết kiệm xăng không?', 'ACTIVE', '2024-03-31 10:40:00'),
    (13, 14, 5, N'VinFast Klara S so với A2 khác gì ạ?', 'ACTIVE', '2024-04-02 13:25:00'),
    (14, 15, 5, N'Pin VinFast chính hãng giá này ổn không?', 'ACTIVE', '2024-04-03 09:50:00'),
    (15, 6, 4, N'VinFast Ludo phù hợp cho sinh viên không?', 'ACTIVE', '2024-04-04 14:35:00'),
    (16, 7, 5, N'Pin Bosch chất lượng thật sự tốt!', 'ACTIVE', '2024-04-05 11:15:00'),
    (17, 8, 4, N'Honda Lead 125 này có phanh ABS không?', 'ACTIVE', '2024-04-06 16:45:00'),
    (18, 9, 5, N'Yamaha FreeGo màu xanh này đẹp quá!', 'ACTIVE', '2024-04-07 12:30:00'),
    (19, 10, 5, N'Pin Yadea 45Ah chạy xa thật không ạ?', 'ACTIVE', '2024-04-08 10:25:00'),
    (20, 2, 4, N'Yadea Like 200 giống Vespa thật không?', 'ACTIVE', '2024-04-09 15:10:00');

-- =============================================
-- 11. BẢNG TRANSACTIONS (Phụ thuộc: members, posts)
-- =============================================
INSERT INTO transactions (buyer_id, post_id, status, created_at)
VALUES
    (5, 1, 'COMPLETED', CAST('2024-03-25 10:00:00' AS DATETIME)),
    (2, 2, 'PENDING', CAST('2024-03-26 14:30:00' AS DATETIME)),
    (5, 4, 'COMPLETED', CAST('2024-03-27 16:20:00' AS DATETIME)),
    (7, 6, 'COMPLETED', CAST('2024-03-28 11:15:00' AS DATETIME)),
    (8, 7, 'PENDING', CAST('2024-03-29 13:40:00' AS DATETIME)),
    (6, 8, 'COMPLETED', CAST('2024-03-30 15:25:00' AS DATETIME)),
    (10, 9, 'COMPLETED', CAST('2024-04-01 09:50:00' AS DATETIME)),
    (11, 10, 'PENDING', CAST('2024-04-02 14:15:00' AS DATETIME)),
    (12, 11, 'COMPLETED', CAST('2024-04-03 16:30:00' AS DATETIME)),
    (13, 12, 'PENDING', CAST('2024-04-04 10:45:00' AS DATETIME)),
    (14, 13, 'COMPLETED', CAST('2024-04-05 12:20:00' AS DATETIME)),
    (15, 14, 'PENDING', CAST('2024-04-06 15:55:00' AS DATETIME)),
    (6, 15, 'COMPLETED', CAST('2024-04-07 11:10:00' AS DATETIME)),
    (7, 16, 'PENDING', CAST('2024-04-08 13:35:00' AS DATETIME)),
    (8, 17, 'COMPLETED', CAST('2024-04-09 16:45:00' AS DATETIME));

-- =============================================
-- 12. BẢNG CONTRACTS (Phụ thuộc: transactions)
-- =============================================
INSERT INTO contracts (transaction_id, contract_url, signed_at, status, created_at)
VALUES
    (1, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-03-25 11:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-25 10:30:00' AS DATETIME)),
    (3, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-03-27 17:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-27 16:45:00' AS DATETIME)),
    (4, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-03-28 12:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-28 11:45:00' AS DATETIME)),
    (6, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-03-30 16:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-30 15:45:00' AS DATETIME)),
    (7, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-04-01 10:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-01 10:15:00' AS DATETIME)),
    (9, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-04-03 17:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-03 17:00:00' AS DATETIME)),
    (11, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-04-05 13:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-05 12:45:00' AS DATETIME)),
    (13, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-04-07 12:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-07 11:45:00' AS DATETIME)),
    (15, 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', CAST('2024-04-09 17:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-09 17:15:00' AS DATETIME));

-- =============================================
-- 13. BẢNG COMMISSIONS (Phụ thuộc: transactions)
-- =============================================
INSERT INTO commissions (transaction_id, commission_rate, amount, status, created_at)
VALUES
    (1, 0.05, 3250000.00, 'PAID', CAST('2024-03-25 12:00:00' AS DATETIME)),
    (3, 0.05, 1600000.00, 'PAID', CAST('2024-03-27 18:00:00' AS DATETIME)),
    (4, 0.05, 1100000.00, 'PAID', CAST('2024-03-28 13:00:00' AS DATETIME)),
    (6, 0.05, 600000.00, 'PAID', CAST('2024-03-30 17:00:00' AS DATETIME)),
    (7, 0.05, 4250000.00, 'PAID', CAST('2024-04-01 11:30:00' AS DATETIME)),
    (9, 0.05, 750000.00, 'PAID', CAST('2024-04-03 18:00:00' AS DATETIME)),
    (11, 0.05, 2250000.00, 'PAID', CAST('2024-04-05 14:00:00' AS DATETIME)),
    (13, 0.05, 1450000.00, 'PAID', CAST('2024-04-07 13:00:00' AS DATETIME)),
    (15, 0.05, 1300000.00, 'PAID', CAST('2024-04-09 18:30:00' AS DATETIME));

-- =============================================
-- 14. BẢNG REVIEWS (Phụ thuộc: members, transactions)
-- =============================================
INSERT INTO reviews (seller_id, reviewer_id, transaction_id, rating, comment, status, created_at)
VALUES
    (2, 5, 1, 5, N'Người bán rất tốt, xe đúng như mô tả. Giao dịch nhanh chóng!', 'ACTIVE', CAST('2024-03-26 10:00:00' AS DATETIME)),
    (4, 5, 3, 5, N'Xe VinFast rất đẹp, người bán nhiệt tình. Recommend!', 'ACTIVE', CAST('2024-03-28 09:30:00' AS DATETIME)),
    (6, 7, 4, 4, N'Honda Vision chất lượng tốt, giá hợp lý. Cảm ơn bạn!', 'ACTIVE', CAST('2024-03-29 08:45:00' AS DATETIME)),
    (8, 6, 6, 5, N'Pin Samsung chất lượng cao, đúng như quảng cáo. Rất hài lòng!', 'ACTIVE', CAST('2024-03-31 16:20:00' AS DATETIME)),
    (9, 10, 7, 5, N'Vespa Primavera đẹp xuất sắc! Người bán tư vấn nhiệt tình.', 'ACTIVE', CAST('2024-04-02 11:15:00' AS DATETIME)),
    (11, 12, 9, 4, N'Pin CATL dung lượng lớn thật sự. Giao hàng đúng hẹn.', 'ACTIVE', CAST('2024-04-04 17:45:00' AS DATETIME)),
    (13, 14, 11, 5, N'VinFast Klara S đẹp như mới. Chất lượng tuyệt vời!', 'ACTIVE', CAST('2024-04-06 13:30:00' AS DATETIME)),
    (15, 6, 13, 4, N'VinFast Ludo thiết kế trẻ trung, phù hợp với tôi. Thanks!', 'ACTIVE', CAST('2024-04-08 12:40:00' AS DATETIME)),
    (7, 8, 15, 5, N'Honda Lead 125 máy móc ổn định. Người bán uy tín!', 'ACTIVE', CAST('2024-04-10 09:25:00' AS DATETIME));

-- =============================================
-- VERIFY DATA
-- =============================================
SELECT 'Members' AS TableName, COUNT(*) AS RecordCount FROM members
UNION ALL
SELECT 'Membership Plans', COUNT(*) FROM membership_plans
UNION ALL
SELECT 'Vehicles', COUNT(*) FROM vehicle
UNION ALL
SELECT 'Batteries', COUNT(*) FROM battery
UNION ALL
SELECT 'Products', COUNT(*) FROM products
UNION ALL
SELECT 'Posts', COUNT(*) FROM posts
UNION ALL
SELECT 'Post Images', COUNT(*) FROM post_images
UNION ALL
SELECT 'Member Plan Usage', COUNT(*) FROM member_plan_usage
UNION ALL
SELECT 'Favorites', COUNT(*) FROM favorites
UNION ALL
SELECT 'Comments', COUNT(*) FROM comments
UNION ALL
SELECT 'Transactions', COUNT(*) FROM transactions
UNION ALL
SELECT 'Contracts', COUNT(*) FROM contracts
UNION ALL
SELECT 'Commissions', COUNT(*) FROM commissions
UNION ALL
SELECT 'Reviews', COUNT(*) FROM reviews;
