-- =============================================
-- SQL INSERT DATA SCRIPT
-- Second-hand EV Battery Trading Platform
-- =============================================

USE carry;

-- =============================================
-- 1. MEMBERS (15 members với các status khác nhau)
-- =============================================
INSERT INTO members (username, address, email, phone, city, password, role, status, avatar_url, created_at)
VALUES
    ('admin', N'123 Đường Nguyễn Huệ, Quận 1', 'admin@carry.com', '0901234567', N'Thành phố Hồ Chí Minh', 'Admin@2024', 'ADMIN', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-01-01 08:00:00' AS DATETIME)),
    ('johndoe', N'456 Đường Lê Lợi, Quận 1', 'john.doe@gmail.com', '0912345678', N'Thành phố Hồ Chí Minh', 'JohnDoe123', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-01-15 10:30:00' AS DATETIME)),
    ('janesmit', N'789 Phố Hoàn Kiếm, Quận Ba Đình', 'jane.smith@gmail.com', '0923456789', N'Hà Nội', 'Jane@Smith99', 'USER', 'SUSPENDED', 'https://i.pravatar.cc/300', CAST('2024-02-01 09:15:00' AS DATETIME)),
    ('bobseller', N'321 Đường Trần Phú, Quận Hải Châu', 'bob.seller@gmail.com', '0934567890', N'Đà Nẵng', 'Bob$Seller22', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-02-10 14:20:00' AS DATETIME)),
    ('alicebuyer', N'654 Đường Hai Bà Trưng, Quận 3', 'alice.buyer@gmail.com', '0945678901', N'Thành phố Hồ Chí Minh', 'Alice2024!', 'USER', 'BANNED', 'https://i.pravatar.cc/300', CAST('2024-03-05 11:45:00' AS DATETIME)),
    ('minh_nguyen', N'123 Đường Nguyễn Thái Học, Quận Ba Đình', 'minh.nguyen@gmail.com', '0956789012', N'Hà Nội', 'MinhNg@2024', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-15 08:30:00' AS DATETIME)),
    ('trang_le', N'456 Đường Lý Thường Kiệt, Quận 10', 'trang.le@gmail.com', '0967890123', N'Thành phố Hồ Chí Minh', 'TrangLe#456', 'USER', 'SUSPENDED', 'https://i.pravatar.cc/300', CAST('2024-03-20 13:10:00' AS DATETIME)),
    ('duc_pham', N'789 Đường Lê Duẩn, Quận Hải Châu', 'duc.pham@gmail.com', '0978901234', N'Đà Nẵng', 'DucPham789!', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-15 16:45:00' AS DATETIME)),
    ('linh_vo', N'321 Đường Hoàng Văn Thụ, Quận Tân Bình', 'linh.vo@gmail.com', '0989012345', N'Thành phố Hồ Chí Minh', 'LinhVo@321', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-22 10:25:00' AS DATETIME)),
    ('huy_tran', N'654 Đường Điện Biên Phủ, Quận Bình Thạnh', 'huy.tran@gmail.com', '0990123456', N'Thành phố Hồ Chí Minh', 'HuyTran$654', 'USER', 'BANNED', 'https://i.pravatar.cc/300', CAST('2024-03-25 15:50:00' AS DATETIME)),
    ('thao_hoang', N'987 Đường Ngô Quyền, Quận Sơn Trà', 'thao.hoang@gmail.com', '0901234568', N'Đà Nẵng', 'ThaoHoang87', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-03-28 09:00:00' AS DATETIME)),
    ('nam_bui', N'147 Đường Trần Hưng Đạo, Quận Hoàn Kiếm', 'nam.bui@gmail.com', '0912345679', N'Hà Nội', 'NamBui#147', 'USER', 'SUSPENDED', 'https://i.pravatar.cc/300', CAST('2024-03-29 14:30:00' AS DATETIME)),
    ('mai_do', N'258 Đường Võ Thị Sáu, Quận 3', 'mai.do@gmail.com', '0923456780', N'Thành phố Hồ Chí Minh', 'MaiDo258@', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-04-01 11:15:00' AS DATETIME)),
    ('long_nguyen', N'369 Đường Phan Châu Trinh, Quận Thanh Khê', 'long.nguyen@gmail.com', '0934567891', N'Đà Nẵng', 'LongNg369!', 'USER', 'ACTIVE', 'https://i.pravatar.cc/300', CAST('2024-04-02 08:40:00' AS DATETIME)),
    ('ha_pham', N'741 Đường Cách Mạng Tháng 8, Quận 10', 'ha.pham@gmail.com', '0945678902', N'Thành phố Hồ Chí Minh', 'HaPham741#', 'USER', 'BANNED', 'https://i.pravatar.cc/300', CAST('2024-04-03 13:20:00' AS DATETIME));

-- =============================================
-- 2. MEMBERSHIP_PLANS (3 plans theo yêu cầu)
-- =============================================
INSERT INTO membership_plans (name, price, duration, max_posts, priority)
VALUES
    ('Free', 0.00, 12, 5, 'LOW'),
    ('Basic', 99000.00, 12, 15, 'MEDIUM'),
    ('Premium', 299000.00, 15, 100, 'HIGH');

-- =============================================
-- 3. VEHICLE (15 vehicles - battery_capacity là số + kWh)
-- =============================================
INSERT INTO vehicle (name, brand, model, mileage, condition, register_year, battery_capacity, origin)
VALUES
    ('VinFast VF e34', 'VinFast', 'VF e34', 15000, 'Excellent', '2022', '42 kWh', N'Việt Nam'),
    ('Tesla Model 3', 'Tesla', 'Model 3', 25000, 'Excellent', '2021', '60 kWh', N'Mỹ'),
    ('Hyundai Kona EV', 'Hyundai', 'Kona Electric', 30000, 'Good', '2020', '64 kWh', N'Hàn Quốc'),
    ('VinFast VF 8', 'VinFast', 'VF 8', 8000, 'New', '2023', '87.7 kWh', N'Việt Nam'),
    ('Nissan Leaf', 'Nissan', 'Leaf', 45000, 'Fair', '2019', '40 kWh', N'Nhật Bản'),
    ('BMW i3', 'BMW', 'i3', 35000, 'Good', '2020', '42.2 kWh', N'Đức'),
    ('Chevrolet Bolt EV', 'Chevrolet', 'Bolt EV', 28000, 'Good', '2021', '66 kWh', N'Mỹ'),
    ('VinFast VF 9', 'VinFast', 'VF 9', 5000, 'New', '2023', '123 kWh', N'Việt Nam'),
    ('Audi e-tron', 'Audi', 'e-tron', 20000, 'Excellent', '2022', '95 kWh', N'Đức'),
    ('Kia EV6', 'Kia', 'EV6', 12000, 'New', '2023', '77.4 kWh', N'Hàn Quốc'),
    ('Mercedes EQC', 'Mercedes-Benz', 'EQC', 18000, 'Good', '2022', '80 kWh', N'Đức'),
    ('Peugeot e-208', 'Peugeot', 'e-208', 22000, 'Good', '2021', '50 kWh', N'Pháp'),
    ('MG ZS EV', 'MG', 'ZS EV', 16000, 'Good', '2022', '44.5 kWh', N'Anh'),
    ('Porsche Taycan', 'Porsche', 'Taycan', 10000, 'New', '2023', '93.4 kWh', N'Đức'),
    ('Hyundai Ioniq 5', 'Hyundai', 'Ioniq 5', 14000, 'Excellent', '2023', '72.6 kWh', N'Hàn Quốc');

-- =============================================
-- 4. BATTERY (15 batteries)
-- =============================================
INSERT INTO battery (name, brand, year_at, voltage_v, capacity_ah, condition, origin)
VALUES
    ('LG 60V 28Ah', 'LG Chem', '2023', '60V', 28, 'New', N'Hàn Quốc'),
    ('Panasonic 48V 20Ah', 'Panasonic', '2022', '48V', 20, 'Good', N'Nhật Bản'),
    ('Samsung 60V 32Ah', 'Samsung', '2023', '60V', 32, 'New', N'Hàn Quốc'),
    ('CATL 72V 40Ah', 'CATL', '2021', '72V', 40, 'Good', N'Trung Quốc'),
    ('BYD 60V 20Ah', 'BYD', '2022', '60V', 20, 'Fair', N'Trung Quốc'),
    ('VinFast 72V 28Ah', 'VinFast', '2024', '72V', 28, 'New', N'Việt Nam'),
    ('Bosch 48V 25Ah', 'Bosch', '2023', '48V', 25, 'Excellent', N'Đức'),
    ('Tesla 60V 35Ah', 'Tesla', '2022', '60V', 35, 'Good', N'Mỹ'),
    ('Pega 60V 30Ah', 'Pega', '2021', '60V', 30, 'Good', N'Việt Nam'),
    ('Yadea 72V 45Ah', 'Yadea', '2023', '72V', 45, 'Excellent', N'Trung Quốc'),
    ('Honda 12V 9Ah', 'Honda', '2022', '12V', 9, 'Good', N'Việt Nam'),
    ('Yamaha 12V 8Ah', 'Yamaha', '2023', '12V', 8, 'Excellent', N'Việt Nam'),
    ('ProMax 48V 22Ah', 'Generic', '2021', '48V', 22, 'Fair', N'Trung Quốc'),
    ('SuperPower 60V 38Ah', 'SuperPower', '2024', '60V', 38, 'New', N'Hàn Quốc'),
    ('EcoTech 72V 42Ah', 'EcoTech', '2022', '72V', 42, 'Good', N'Nhật Bản');

-- =============================================
-- 5. MEMBER_PLAN_USAGE (Mỗi member có 1 plan)
-- =============================================
INSERT INTO member_plan_usage (member_id, plan_id, start_date, end_date, status)
VALUES
    (1, 3, CAST('2024-01-01 00:00:00' AS DATETIME), CAST('2025-04-01 23:59:59' AS DATETIME), 'ACTIVE'),
    (2, 2, CAST('2024-01-15 00:00:00' AS DATETIME), CAST('2025-01-15 23:59:59' AS DATETIME), 'ACTIVE'),
    (3, 1, CAST('2024-02-01 00:00:00' AS DATETIME), CAST('2025-02-01 23:59:59' AS DATETIME), 'ACTIVE'),
    (4, 3, CAST('2024-02-10 00:00:00' AS DATETIME), CAST('2025-05-10 23:59:59' AS DATETIME), 'ACTIVE'),
    (5, 1, CAST('2024-03-05 00:00:00' AS DATETIME), CAST('2025-03-05 23:59:59' AS DATETIME), 'ACTIVE'),
    (6, 2, CAST('2024-03-15 00:00:00' AS DATETIME), CAST('2025-03-15 23:59:59' AS DATETIME), 'ACTIVE'),
    (7, 1, CAST('2024-03-20 00:00:00' AS DATETIME), CAST('2025-03-20 23:59:59' AS DATETIME), 'ACTIVE'),
    (8, 3, CAST('2024-03-15 00:00:00' AS DATETIME), CAST('2025-06-15 23:59:59' AS DATETIME), 'ACTIVE'),
    (9, 3, CAST('2024-03-22 00:00:00' AS DATETIME), CAST('2025-06-22 23:59:59' AS DATETIME), 'ACTIVE'),
    (10, 2, CAST('2024-03-25 00:00:00' AS DATETIME), CAST('2025-03-25 23:59:59' AS DATETIME), 'ACTIVE'),
    (11, 2, CAST('2024-03-28 00:00:00' AS DATETIME), CAST('2025-03-28 23:59:59' AS DATETIME), 'ACTIVE'),
    (12, 3, CAST('2024-03-29 00:00:00' AS DATETIME), CAST('2025-06-29 23:59:59' AS DATETIME), 'ACTIVE'),
    (13, 2, CAST('2024-04-01 00:00:00' AS DATETIME), CAST('2025-04-01 23:59:59' AS DATETIME), 'ACTIVE'),
    (14, 1, CAST('2024-04-02 00:00:00' AS DATETIME), CAST('2025-04-02 23:59:59' AS DATETIME), 'ACTIVE'),
    (15, 1, CAST('2024-04-03 00:00:00' AS DATETIME), CAST('2025-04-03 23:59:59' AS DATETIME), 'ACTIVE');

-- =============================================
-- 6. PRODUCTS (30: 15 VEHICLE + 15 BATTERY)
-- Tên product giới hạn 20 ký tự
-- =============================================
INSERT INTO products (member_id, vehicle_id, battery_id, product_type, name, description, status, created_at)
VALUES
    -- VEHICLE Products (15) - battery_id = NULL
    (2, 1, NULL, 'VEHICLE', 'VF e34 2022', N'VinFast VF e34 đời 2022, pin 42 kWh, đi 15,000km, xe zin chưa té ngã', 'USED', CAST('2024-03-10 10:00:00' AS DATETIME)),
    (3, 2, NULL, 'VEHICLE', 'Tesla Model 3', N'Tesla Model 3 nhập Mỹ, pin 60 kWh, xe đẹp máy ngon', 'USED', CAST('2024-03-12 11:30:00' AS DATETIME)),
    (4, 3, NULL, 'VEHICLE', 'Hyundai Kona EV', N'Hyundai Kona Electric 2020, pin 64 kWh, màu trắng', 'USED', CAST('2024-03-18 09:45:00' AS DATETIME)),
    (6, 4, NULL, 'VEHICLE', 'VinFast VF 8', N'VinFast VF 8 pin 87.7 kWh, màu xám bạc, đi 8,000km', 'NEW', CAST('2024-03-22 08:30:00' AS DATETIME)),
    (7, 5, NULL, 'VEHICLE', 'Nissan Leaf 2019', N'Nissan Leaf pin 40 kWh, màu đen, chỉ đi 45,000km', 'USED', CAST('2024-03-24 10:15:00' AS DATETIME)),
    (9, 6, NULL, 'VEHICLE', 'BMW i3 2020', N'BMW i3 pin 42.2 kWh màu xanh mint, xe Đức nhập khẩu', 'USED', CAST('2024-03-27 15:20:00' AS DATETIME)),
    (10, 7, NULL, 'VEHICLE', 'Chevrolet Bolt EV', N'Chevrolet Bolt EV pin 66 kWh mới 100%', 'USED', CAST('2024-03-28 11:00:00' AS DATETIME)),
    (12, 8, NULL, 'VEHICLE', 'VinFast VF 9', N'VinFast VF 9 pin 123 kWh màu trắng đen, sporty', 'NEW', CAST('2024-03-30 09:20:00' AS DATETIME)),
    (13, 9, NULL, 'VEHICLE', 'Audi e-tron 2022', N'Audi e-tron pin 95 kWh phiên bản cao cấp', 'USED', CAST('2024-04-01 16:40:00' AS DATETIME)),
    (15, 10, NULL, 'VEHICLE', 'Kia EV6 2023', N'Kia EV6 pin 77.4 kWh mới, thiết kế trẻ trung', 'NEW', CAST('2024-04-03 10:50:00' AS DATETIME)),
    (7, 11, NULL, 'VEHICLE', 'Mercedes EQC 2022', N'Mercedes EQC pin 80 kWh màu đỏ, xe gia đình', 'USED', CAST('2024-04-05 11:10:00' AS DATETIME)),
    (8, 12, NULL, 'VEHICLE', 'Peugeot e-208', N'Peugeot e-208 pin 50 kWh màu xanh dương', 'USED', CAST('2024-04-06 14:45:00' AS DATETIME)),
    (10, 13, NULL, 'VEHICLE', 'MG ZS EV 2022', N'MG ZS EV pin 44.5 kWh thiết kế đẹp, giá tốt', 'USED', CAST('2024-04-08 15:55:00' AS DATETIME)),
    (4, 14, NULL, 'VEHICLE', 'Porsche Taycan', N'Porsche Taycan pin 93.4 kWh màu xanh, chạy xa', 'NEW', CAST('2024-04-09 10:30:00' AS DATETIME)),
    (6, 15, NULL, 'VEHICLE', 'Hyundai Ioniq 5', N'Hyundai Ioniq 5 pin 72.6 kWh đời 2023', 'USED', CAST('2024-04-10 13:20:00' AS DATETIME)),

    -- BATTERY Products (15) - vehicle_id = NULL
    (4, NULL, 1, 'BATTERY', 'Pin LG 60V 28Ah', N'Pin Lithium LG 60V 28Ah mới 100%, bảo hành 2 năm', 'NEW', CAST('2024-03-15 14:20:00' AS DATETIME)),
    (2, NULL, 2, 'BATTERY', 'Pin Panasonic 48V', N'Pin Panasonic 48V 20Ah đã dùng 1 năm, còn tốt', 'USED', CAST('2024-03-20 16:10:00' AS DATETIME)),
    (8, NULL, 3, 'BATTERY', 'Pin Samsung 60V', N'Pin Samsung SDI 60V 32Ah chất lượng cao, mới 100%', 'NEW', CAST('2024-03-25 13:45:00' AS DATETIME)),
    (11, NULL, 4, 'BATTERY', 'Pin CATL 72V', N'Pin CATL 72V 40Ah dung lượng lớn, xe cao cấp', 'USED', CAST('2024-03-29 14:30:00' AS DATETIME)),
    (14, NULL, 5, 'BATTERY', 'Pin BYD 60V', N'Pin BYD 60V 20Ah chính hãng, bảo hành dài hạn', 'USED', CAST('2024-04-02 12:15:00' AS DATETIME)),
    (6, NULL, 6, 'BATTERY', 'Pin VinFast 72V', N'Pin VinFast 72V 28Ah Đức, chất lượng châu Âu', 'NEW', CAST('2024-04-04 13:25:00' AS DATETIME)),
    (9, NULL, 7, 'BATTERY', 'Pin Bosch 48V', N'Pin Bosch 48V 25Ah tầm xa, dung lượng 45Ah', 'NEW', CAST('2024-04-07 09:35:00' AS DATETIME)),
    (2, NULL, 8, 'BATTERY', 'Pin Tesla 60V', N'Pin Tesla 60V 35Ah đã qua sử dụng, còn tốt 80%', 'USED', CAST('2024-04-08 11:00:00' AS DATETIME)),
    (5, NULL, 9, 'BATTERY', 'Pin Pega 60V', N'Pin Pega 60V 30Ah chất lượng cao, bền bỉ', 'USED', CAST('2024-04-09 14:30:00' AS DATETIME)),
    (8, NULL, 10, 'BATTERY', 'Pin Yadea 72V', N'Pin Yadea 72V 45Ah High Performance, giá tốt', 'NEW', CAST('2024-04-10 10:15:00' AS DATETIME)),
    (11, NULL, 11, 'BATTERY', 'Pin Honda 12V', N'Pin Honda 12V 9Ah chính hãng, phù hợp xe ga', 'USED', CAST('2024-04-11 09:00:00' AS DATETIME)),
    (13, NULL, 12, 'BATTERY', 'Pin Yamaha 12V', N'Pin Yamaha 12V 8Ah original, chất lượng Nhật', 'NEW', CAST('2024-04-12 15:20:00' AS DATETIME)),
    (14, NULL, 13, 'BATTERY', 'Pin ProMax 48V', N'Pin Lithium Pro Max 48V 22Ah giá rẻ', 'USED', CAST('2024-04-13 11:45:00' AS DATETIME)),
    (15, NULL, 14, 'BATTERY', 'Pin SuperPower 60V', N'Pin SuperPower 60V 38Ah mới 100%', 'NEW', CAST('2024-04-14 13:10:00' AS DATETIME)),
    (12, NULL, 15, 'BATTERY', 'Pin EcoTech 72V', N'Pin EcoTech Green Battery 72V 42Ah', 'USED', CAST('2024-04-15 16:30:00' AS DATETIME));

-- =============================================
-- 7. POSTS (30 posts)
-- =============================================
INSERT INTO posts (product_id, seller_id, title, description, status, price, created_at)
VALUES
    -- VEHICLE POSTS (15)
    (1, 2, N'Bán VinFast VF e34 2022', N'VinFast VF e34 đời 2022, pin 42 kWh, đi 15,000km. Xe zin chưa té ngã, máy êm ru.', 'APPROVED', 650000000.00, CAST('2024-03-10 10:00:00' AS DATETIME)),
    (2, 3, N'Tesla Model 3 2021 nhập Mỹ', N'Tesla Model 3 nhập Mỹ, pin 60 kWh. Xe đẹp máy ngon, Autopilot. Cần bán gấp.', 'PENDING', 1250000000.00, CAST('2024-03-12 11:30:00' AS DATETIME)),
    (3, 4, N'Hyundai Kona Electric 2020', N'Hyundai Kona Electric 2020, pin 64 kWh, đi 30,000km. Xe như mới.', 'REJECTED', 580000000.00, CAST('2024-03-18 09:45:00' AS DATETIME)),
    (4, 6, N'VinFast VF 8 2023', N'VinFast VF 8 2023, pin 87.7 kWh, màu xám bạc. Xe cao cấp.', 'APPROVED', 1050000000.00, CAST('2024-03-22 08:30:00' AS DATETIME)),
    (5, 7, N'Nissan Leaf 2019 giá rẻ', N'Nissan Leaf 2019, pin 40 kWh, chỉ đi 45,000km. Xe nhập khẩu.', 'APPROVED', 480000000.00, CAST('2024-03-24 10:15:00' AS DATETIME)),
    (6, 9, N'BMW i3 2020 sang trọng', N'BMW i3 2020, pin 42.2 kWh màu xanh mint. Xe Đức nhập khẩu.', 'APPROVED', 780000000.00, CAST('2024-03-27 15:20:00' AS DATETIME)),
    (7, 10, N'Chevrolet Bolt EV 2021', N'Chevrolet Bolt EV 2021, pin 66 kWh. Xe điện Mỹ chất lượng.', 'REJECTED', 550000000.00, CAST('2024-03-28 11:00:00' AS DATETIME)),
    (8, 12, N'VinFast VF 9 2023', N'VinFast VF 9 2023, pin 123 kWh, màu trắng đen, sporty.', 'APPROVED', 1550000000.00, CAST('2024-03-30 09:20:00' AS DATETIME)),
    (9, 13, N'Audi e-tron 2022 cao cấp', N'Audi e-tron 2022, pin 95 kWh phiên bản cao cấp, full option.', 'APPROVED', 2200000000.00, CAST('2024-04-01 16:40:00' AS DATETIME)),
    (10, 15, N'Kia EV6 2023 mới', N'Kia EV6 2023, pin 77.4 kWh mới ra mắt, thiết kế trẻ trung.', 'PENDING', 1350000000.00, CAST('2024-04-03 10:50:00' AS DATETIME)),
    (11, 7, N'Mercedes EQC 2022', N'Mercedes EQC 2022, pin 80 kWh màu đỏ, xe gia đình.', 'APPROVED', 2500000000.00, CAST('2024-04-05 11:10:00' AS DATETIME)),
    (12, 8, N'Peugeot e-208 2021', N'Peugeot e-208 2021, pin 50 kWh màu xanh dương, trẻ trung.', 'PENDING', 680000000.00, CAST('2024-04-06 14:45:00' AS DATETIME)),
    (13, 10, N'MG ZS EV 2022 giá tốt', N'MG ZS EV 2022, pin 44.5 kWh thiết kế đẹp, giá tốt.', 'REJECTED', 520000000.00, CAST('2024-04-08 15:55:00' AS DATETIME)),
    (14, 4, N'Porsche Taycan 2023', N'Porsche Taycan 2023, pin 93.4 kWh màu xanh, chạy xa.', 'APPROVED', 4500000000.00, CAST('2024-04-09 10:30:00' AS DATETIME)),
    (15, 6, N'Hyundai Ioniq 5 2023', N'Hyundai Ioniq 5 2023, pin 72.6 kWh đời 2023.', 'APPROVED', 1280000000.00, CAST('2024-04-10 13:20:00' AS DATETIME)),

    -- BATTERY POSTS (15)
    (16, 4, N'Pin LG 60V 28Ah chính hãng', N'Pin Lithium LG 60V 28Ah mới 100%, bảo hành 2 năm.', 'APPROVED', 12000000.00, CAST('2024-03-15 14:20:00' AS DATETIME)),
    (17, 2, N'Pin Panasonic 48V giá rẻ', N'Pin Panasonic 48V 20Ah đã dùng 1 năm, còn tốt.', 'PENDING', 3500000.00, CAST('2024-03-20 16:10:00' AS DATETIME)),
    (18, 8, N'Pin Samsung SDI 60V', N'Pin Samsung SDI 60V 32Ah mới 100%, chất lượng cao.', 'APPROVED', 15000000.00, CAST('2024-03-25 13:45:00' AS DATETIME)),
    (19, 11, N'Pin CATL 72V dung lượng lớn', N'Pin CATL 72V 40Ah dung lượng lớn, phù hợp xe cao cấp.', 'PENDING', 18000000.00, CAST('2024-03-29 14:30:00' AS DATETIME)),
    (20, 14, N'Pin BYD 60V chính hãng', N'Pin BYD 60V 20Ah chính hãng, bảo hành dài hạn.', 'REJECTED', 7000000.00, CAST('2024-04-02 12:15:00' AS DATETIME)),
    (21, 6, N'Pin VinFast 72V VN', N'Pin VinFast 72V 28Ah made in Vietnam, chất lượng cao.', 'APPROVED', 20000000.00, CAST('2024-04-04 13:25:00' AS DATETIME)),
    (22, 9, N'Pin Bosch 48V Đức', N'Pin Bosch 48V 25Ah tầm xa, chất lượng Đức.', 'APPROVED', 11000000.00, CAST('2024-04-07 09:35:00' AS DATETIME)),
    (23, 2, N'Pin Tesla 60V giá tốt', N'Pin Tesla 60V 35Ah đã qua sử dụng, còn tốt 80%.', 'APPROVED', 9000000.00, CAST('2024-04-08 11:00:00' AS DATETIME)),
    (24, 5, N'Pin Pega 60V chất lượng', N'Pin Pega 60V 30Ah chất lượng cao, bền bỉ.', 'PENDING', 8000000.00, CAST('2024-04-09 14:30:00' AS DATETIME)),
    (25, 8, N'Pin Yadea 72V tầm xa', N'Pin Yadea 72V 45Ah High Performance, chạy xa.', 'APPROVED', 13000000.00, CAST('2024-04-10 10:15:00' AS DATETIME)),
    (26, 11, N'Pin Honda 12V chính hãng', N'Pin Honda 12V 9Ah chính hãng, phù hợp xe ga Honda.', 'REJECTED', 800000.00, CAST('2024-04-11 09:00:00' AS DATETIME)),
    (27, 13, N'Pin Yamaha 12V Original', N'Pin Yamaha 12V 8Ah original, chất lượng Nhật Bản.', 'APPROVED', 850000.00, CAST('2024-04-12 15:20:00' AS DATETIME)),
    (28, 14, N'Pin ProMax 48V giá rẻ', N'Pin Lithium Pro Max 48V 22Ah giá rẻ cho sinh viên.', 'PENDING', 4500000.00, CAST('2024-04-13 11:45:00' AS DATETIME)),
    (29, 15, N'Pin SuperPower 60V mới', N'Pin SuperPower 60V 38Ah mới 100%, bảo hành 1 năm.', 'APPROVED', 14000000.00, CAST('2024-04-14 13:10:00' AS DATETIME)),
    (30, 12, N'Pin EcoTech 72V xanh', N'Pin EcoTech Green Battery 72V 42Ah, thân thiện môi trường.', 'APPROVED', 16000000.00, CAST('2024-04-15 16:30:00' AS DATETIME));

-- =============================================
-- 8. POST_IMAGES (Mỗi post có ít nhất 1 hình)
-- =============================================
INSERT INTO post_images (post_id, image_url)
VALUES
    -- Vehicle Posts (1-15)
    (1, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/577120807_1569830467510934_3319337389530102889_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=if5HLlg_fiUQ7kNvwFiD8_d&_nc_oc=Adm7AMx0gDmYWgXToK63DgzuqHOa2joHDZhOc2ZPRl_lgfGvTTlN79ZKgP5m04bwrzE&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=DYf8oV5n82nq9nmKuccIWw&oh=00_AfjF3CbHo1LjFKUGr9MccuDK4m9GsuhTFigWglCvix82-A&oe=6911130F
'), (1, 'https://i.pravatar.cc/300'), (1, 'https://i.pravatar.cc/300'),
    (2, 'https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/574402052_25163778806641911_7369394568771902538_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=VchdVS3yJywQ7kNvwHq0Vvz&_nc_oc=AdkIjkqWNqAwxI6xaPbR_wLdSbOuDYoI7YGfQ4s02HfMRTxc83pXP4W-693KjEKRZMI&_nc_zt=23&_nc_ht=scontent.fsgn5-5.fna&_nc_gid=FN6wHBG3rG2ZyJ5WWuccQA&oh=00_Afjpb9TBLMWVddwYNirDVZA5SofYR6pONsqwEpzYcJVA1A&oe=69110EF4'),
    (2, 'https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/577314544_25163779473308511_2522452078127447000_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=4Jqbcm-l0fUQ7kNvwH1L-AQ&_nc_oc=AdmhC6M6VY7uKKs9nsf1EMYFpjGIPLXwd-JkZ2aptq33SM7nVYIXekJXxbx42TTrtNI&_nc_zt=23&_nc_ht=scontent.fsgn5-5.fna&_nc_gid=7CF2peD7oWRnuaUwUbVSSw&oh=00_AfiL3zFxUysg2lYRqyEjSbQH05HTPXOFMUOaG9M2FHEmzQ&oe=69111923'),
    (3, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/574576710_4162172634024588_6830645290375897516_n.jpg?stp=dst-jpg_s590x590_tt6&_nc_cat=102&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=tlF2JCGnBbkQ7kNvwHocYWZ&_nc_oc=AdkTuLnbXWLFUnhDDRlw8nQYjRFYPdSWvbdlkyvke8DgmtdYF1fik4FRWqLp6eUPiCU&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=TDbeKjOnDR85zTc2P_fwkA&oh=00_Afjo8yKkzLwRHCPgjtsTCoKao7yX1KGLlsla8gOXtZLNMQ&oe=69111528'),
    (3, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/574571357_4162172854024566_4788697729131629808_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=dlm2bwm71xMQ7kNvwHTk-2B&_nc_oc=AdmJmehERubcqi_xuExb_kfFJwvVSBg2WV7XmkW1MwGvGogksEGDXZbk3i5KCtTI5us&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=CQcHjwtlBliPYyKbpZ4Ttw&oh=00_Afgzj4wM9tgZCkjYkmPgZ6dcswxutGGJkWZubd54yT3V_A&oe=69112C36'),
    (4, 'https://scontent.fsgn5-12.fna.fbcdn.net/v/t39.30808-6/577681947_3723136587820079_1169236147960725602_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=kbitsppAKu4Q7kNvwHyCAqo&_nc_oc=Adk9iYceLJdx6VhlJnxt_Wz8iYmpcZ_7uL5NI4HreIfwCsO2in7R-IGJ1VZMxNmOzyU&_nc_zt=23&_nc_ht=scontent.fsgn5-12.fna&_nc_gid=DKOqAR0JcnCtNkquKULDZQ&oh=00_AfjBMQEG5XmTQYZmn7A65gTpgA870qnQwdgdwLlvCbjlbw&oe=69111CA1'),
    (4, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/577826030_3723136737820064_5717350483886352199_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=oixtdsBSSWAQ7kNvwHgxBNY&_nc_oc=AdlDCjbAkS8iOaB0s0x-YkuRK7HGkgwMKxBIpSKR4332drP4MKOdRTzIgV8OSlMqP9E&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=F6LVLrXbISttmCrVYUfI1Q&oh=00_Afg54LQV-obBSHImD4fn2kVmsJUhq128k8MafzFr3I5zIg&oe=6911169A'),
    (5, 'https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/577800903_811260371884169_6590700867106216785_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=108&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=OTKoEeRBNgIQ7kNvwGEI1bt&_nc_oc=AdlTJyXTmrIjcu4Fc-EqBgEJ_9Nbsm973ZJLmGqlmOLdmophY90RFAFtrzWaPq78M7s&_nc_zt=23&_nc_ht=scontent.fsgn5-5.fna&_nc_gid=SNyHmWYeiArFOMhEPMpg8A&oh=00_Afj0lWnTGLzlJWp6Z-tffeVbSw_drg93_V-OdtKTrhbnTw&oe=6911259E'),
    (5, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/577071481_811260525217487_4372119941302352518_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=110&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=8hcAU66MnOAQ7kNvwE-8hte&_nc_oc=Admsfdpn2hXbvyWCECx97zq-MfR4o87wn5fhCgvEOhV9XGfCLkdkGkYb5urwaJ3QONY&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=cx1CTOqVamrutcy3Yg8xDg&oh=00_AfjYoaYNd2XbTQNvHRuv4ojQ8HfyaViiXVtstqNKx-kTEw&oe=69110997'),
    (6, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/577304648_25167546279598497_2274224230535192165_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=n_2kf7wkSmgQ7kNvwFRrxsu&_nc_oc=Adm91Hqg9XKvmhIQRCXAPtPwo5rzfQeSw44rPm_nNziOuBcTOAI-zo-v4wurM9_SH84&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=5WHfYNwvgZFp6TAbsR-rlA&oh=00_AfjBG_16E-w88TpYrg7q-szAe_8TYVb_-7oc8tH2O-Vc9w&oe=69110C78'),
    (6, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/574370086_25167547232931735_1370379648409090580_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=NDVLSprisGwQ7kNvwFY-Giy&_nc_oc=AdkNiEFsvg-8K0lUtga8XQaiix7HatRwsIPSlny-8mF0Chti0hDM_rTwXWEX-HMPUag&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=HwY9TCz1_IjRxVcSJajI1A&oh=00_AfhtSJ1cRzJ-8sp8gQLqUabqsMkdXzhH9yLg0JzdDE0Meg&oe=69111F27'),
    (7, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/574862505_1370256024814835_6102765605495124038_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=2APxptHl6SQQ7kNvwHle_7Q&_nc_oc=Adncne4crg_wYCdr9J4KpiCLlavYPzAPdIg2iE1dETrUK7n0bsJexzLHboef65-xdQ0&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=TmczVIe1sl5CtE6jmEEMrQ&oh=00_Afhnv4CMR64s1OMJewUbRnVYumurwZU6TmPumt5mQz454w&oe=69111F8D'),
    (7, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/574276437_1370256071481497_1121513412604557178_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=WNTeHre7KBcQ7kNvwER73TA&_nc_oc=AdlpeIk8Nm48BzhxemnlS3ZhNtedO4ayk3xr4s-ytn96kJbWi93x8uyU6VFxmY7oCnU&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=ipE3LiuW9DpHKrGeUumLgQ&oh=00_AfgF_sBynFDY5tTolAXVioDflGJ1LpT8tiG5rGQrD6aMHw&oe=691128D9'),
    (8, 'https://scontent.fsgn5-3.fna.fbcdn.net/v/t39.30808-6/574366101_1988618015221623_2774854879451930826_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=104&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=xMSsfNn1yDQQ7kNvwGutofX&_nc_oc=AdlcVDqmbwPJR7wjQutSz4cRqPNKpLW_57-s4wK9g6DF2XDhfZx5V3Gdkyz6xvKF-Z0&_nc_zt=23&_nc_ht=scontent.fsgn5-3.fna&_nc_gid=oVg9XnQ1AU5-HjTdLw9Zdw&oh=00_AfhipTrrneJZfTvGf4tn_zAHMVSZbyW5VLEt-UYkWth2Eg&oe=691136FA'),
    (8, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/576664857_1988618038554954_850588882628688747_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=101&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=6yzYCySmr4kQ7kNvwG7bTSw&_nc_oc=AdlgdjBovYJIbI0n8PWQKzGlDvgbcRGLMJ8_jzE8rZG0h8_ShLHe6P90Pb2E1rQXKt4&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=j4sJOzxiU4yldwFyx_Q7eA&oh=00_AfiKut5293e8vejS_qJssD3f9ybSUMd2NlKpF2fzEGNmdQ&oe=69110880'),
    (8, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/573059231_1988618021888289_849364233536971976_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=106&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=fCmQ2lDj-VAQ7kNvwFV9ns0&_nc_oc=AdnL10u8pbeWtX7G5_QIfELie3VnCdKus2c44JHHLSGx-HtN61Eyquhflxv_pbpZiOk&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=AkdLusZAQmdMwjUVW2PgbQ&oh=00_Afjm6qiojOKArRjcQ7i83FVoQsZIJWsHYCfnrTQa60JNqw&oe=69112687'),
    (9, 'https://scontent.fsgn5-3.fna.fbcdn.net/v/t39.30808-6/576532237_809704035373136_5141972148732905453_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=U0q2bjWIU50Q7kNvwH90qgv&_nc_oc=AdklY86TqCxnu2qZVJJl80rBu6EwbEs-S-U6GCu4snJ0jbvYYdhpveJj2l85lrztmMM&_nc_zt=23&_nc_ht=scontent.fsgn5-3.fna&_nc_gid=4WSsl35-8dqwpIErCqkm5g&oh=00_AfhMxC2NKTHGp3DTjPgZ3UIEPeU2DgjQpENKFiOWQyEWdA&oe=69112637'),
    (9, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/576369554_809704558706417_6034476598626247732_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=Tdi8pm_mr1sQ7kNvwFmu5QB&_nc_oc=Admxe7GFAD4eE8Pb0t7q9gst0hQY1XIMsVK_MPY1SfmHx7fGmmD7saNVI_8k7UCcpPw&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=JBxj5QDQg4tegg2bWMGz3A&oh=00_Afgiw57h66PqBvNCZ8pbCTSbaj01qxkwIOU71ePZ9hO8Jw&oe=69111676'),
    (10, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/573046861_2237260383421353_9014583291988152523_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=106&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=5yipzRyXCo0Q7kNvwFtA7Yx&_nc_oc=AdkWmrwCwwxdhpZ1yvow_YhldYo9JPbgp4yqubq2E-l99IRpVlh-JqZdFh0yzfUAbkA&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=m6fyeL5cmXxheYjk-I__fQ&oh=00_AfiJvmVH6yr2YoeGnR_vlmEwYtauRd66WE_j_Y5bxt_W9Q&oe=69110C6F'),
    (10, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/573892260_2237260356754689_9136409281280769876_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=102&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=Kw6Zc9ojCs8Q7kNvwHJi1ce&_nc_oc=AdkCBeFRNEz2pAfbeJxjp1sK_PIwp7YkwpuYjywzByNLix_wJqrMnXqgoKYhcnNu8b0&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=WsfYqvBBXWr8d9HVZSTS-g&oh=00_Afjan3QSiuMl1ZPMsCj4lAyyXcIj5PVusq38uucdhVcqTQ&oe=69110C8F'),
    (11, 'https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/573517664_4127224747543945_7585593994940860355_n.jpg?stp=dst-jpg_s590x590_tt6&_nc_cat=108&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=2496DqT3pJ4Q7kNvwEuHccP&_nc_oc=AdnWs_I3wVmizsY_DE365M1bwPO72a1UgPbA4G7Rd65U5BDTza49RBUkdq5CNdqHL1c&_nc_zt=23&_nc_ht=scontent.fsgn5-5.fna&_nc_gid=YxzzCtoKtOg8sJQr9liG0g&oh=00_AfhsUtezeYudvAnwQKoQmhLvMPaLOzsqhYGN65GBjDFu3A&oe=691105C2'),
    (11, 'https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/574278989_4127224814210605_4347833137157806467_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=2JmWuNpIi2EQ7kNvwF2gzHw&_nc_oc=AdlyiSmaOjZKex7N7qbfHljZahFvCeGmxyGgRgAztK9n7FnANbnd2FE0a3750ZhCZ5Y&_nc_zt=23&_nc_ht=scontent.fsgn5-5.fna&_nc_gid=jBpvUKn_CKeOPmkKVJySVg&oh=00_Afg7mPXT_lWoc7fC_jV3LfbNdQN3kB_E9NU0frlkTCSGqQ&oe=691122C8'),
    (12, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/577337313_25161926550160470_1308385093924027660_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=frCRrNvd-qUQ7kNvwEfXpdV&_nc_oc=AdkE5k11nr4KRX62pb9GHvh-c1YIFeVkTJgi4XIUBEvkRDaHH5N3wp06xzrxWh5s4js&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=h09qZJuKJTiSZ4OCryUgrA&oh=00_Afi-1C_vCm0e1gUSpdPA3tKa70aNSbXbFLelqcWYCHVdMg&oe=69113745'),
    (12, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/577316862_25161927176827074_4605139691861438067_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=JJw21Vd0joEQ7kNvwExUXi1&_nc_oc=AdmSlobzqnDaycWPw040XlYn5V6GveOpFNtbUxxz0msnyGwY_sqj_VYCkc0s9Mya0sI&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=GIW6nrx8hPP6geEUcwIFpw&oh=00_AfjxaFBl2C_AzwaaXanY-5c0AwaHjiIreTZaB9k3yqWV7w&oe=69112BB7'),
    (13, 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/575086337_1512032463280929_1832408448629343382_n.jpg?stp=cp6_dst-jpg_tt6&_nc_cat=105&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=SyHzqVvhpDQQ7kNvwGBgWJq&_nc_oc=Adk361MC6TEXlAnr7mGnRSMt-c9eRVHjwZnCRSsA5fFIsG24Akgxp0M0AiSJh_WRyTE&_nc_zt=23&_nc_ht=scontent.fsgn5-9.fna&_nc_gid=bs28qmkem4TQ6Zihg_Nahg&oh=00_AfgWsRD3gjGAM2sh0VWVzL0fvLs-xef4WX1Q6UqN4WJ0tw&oe=691126F3'),
    (13, 'https://scontent.fsgn5-11.fna.fbcdn.net/v/t39.30808-6/576924871_1512029796614529_966014428521171447_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=IZH6Yr6sg50Q7kNvwFl5rsS&_nc_oc=AdmckRjS9pWvCMsrQy1WO-4Bw6E2jetpogiMC_3Jrzqc6Sxfyl-Tqag8C5zSqUZM4jw&_nc_zt=23&_nc_ht=scontent.fsgn5-11.fna&_nc_gid=_n1jmLai1muEVpIF-WyLyg&oh=00_Afj-UetTtgtKxwMit7lANUFFdOgVe4NSdnBev4jFWdcwAA&oe=6911324A'),
    (14, 'https://scontent.fsgn5-12.fna.fbcdn.net/v/t39.30808-6/575121135_2312389712611174_4354494860869610955_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=V2RO9KiskHAQ7kNvwENREwj&_nc_oc=AdmXWMMu7WlKJ4fdYT8wMVdX3cAXhQNkgcEGVXtIsqhDsL0pW5T1J1qYGJBz0QR_YtQ&_nc_zt=23&_nc_ht=scontent.fsgn5-12.fna&_nc_gid=zEzrZkV3wlVSoXc2o4SIIA&oh=00_Afj7sGp6lUxRDQph3Vjgb9qC1LpE5sWNfNtPPltcPUNQSA&oe=69110660'),
    (14, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/575165881_2312389835944495_3688426437720183569_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=lBctl3C15BoQ7kNvwHqzokZ&_nc_oc=AdlHdeq6G0Rkqbf-qKXgZyetfl0YzS4vynTxvfC0p-hkzmB4HG2ZQCNwgVXJnSg-GR0&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=DuRaOAeRwFhvgKEeMHjt6g&oh=00_Afi9prZK3SEL94yS2GxE2o7doqc4VsuRHfF2aLsF1rDo2A&oe=691130C4'),
    (14, 'https://scontent.fsgn5-10.fna.fbcdn.net/v/t39.30808-6/576076273_2312389719277840_6277388169642864525_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=3m_anHx99UgQ7kNvwHoapnt&_nc_oc=Adnbjn_P-sfalt6CkFYWIKlwyoce-GadVgG48-LVsNnqtXoYXCG4X8q4yDdZkicOsNY&_nc_zt=23&_nc_ht=scontent.fsgn5-10.fna&_nc_gid=qHq3qNYxG-NZSYi9R9Tp5g&oh=00_AfgI3CpvsWCD6PJXe1XS6XIaCqG2NMEc7dYmuBOTvCi31g&oe=69112E1F'),
    (15, 'https://scontent.fsgn5-8.fna.fbcdn.net/v/t39.30808-6/576511080_1477813486831359_1576378729043426845_n.jpg?stp=cp6_dst-jpegr_tt6&_nc_cat=109&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=jLd011aHRf4Q7kNvwGsswPS&_nc_oc=AdlgBhONM0FPnjNmoAKDG2zq6_x7gwJgCufbrLh9T8jzesdrc65hg6o_Ca1v5NxWElE&_nc_zt=23&se=-1&_nc_ht=scontent.fsgn5-8.fna&_nc_gid=yAkLhMhp61hoOUOnCujRBw&oh=00_Afhj8leLKvxaPKLusKoTz8H0mDITWyaibjeItUuVOsDBZg&oe=69110B32'),
    (15, 'https://scontent.fsgn5-11.fna.fbcdn.net/v/t39.30808-6/577446275_1477813600164681_6863714725651480238_n.jpg?stp=cp6_dst-jpegr_tt6&_nc_cat=111&ccb=1-7&_nc_sid=aa7b47&_nc_ohc=MuK_M0oTSY8Q7kNvwEY7rNh&_nc_oc=AdnGrx57fEVY5DVADj-T-H5n81wc-Dd_Q8DKvwRsfeBeBkE9Gt98FnSqv5L_RnllISg&_nc_zt=23&se=-1&_nc_ht=scontent.fsgn5-11.fna&_nc_gid=nPneBr-hMuSyPdjiTxBYaQ&oh=00_AfhbMVw5c9DD1-vtdSjc6Io_P_v8S4D5qPuxDF_E5h8DVw&oe=691114D0'),

    -- Battery Posts (16-30)
    (16, 'https://i.pravatar.cc/300'), (16, 'https://i.pravatar.cc/300'),
    (17, 'https://i.pravatar.cc/300'),
    (18, 'https://i.pravatar.cc/300'), (18, 'https://i.pravatar.cc/300'),
    (19, 'https://i.pravatar.cc/300'),
    (20, 'https://i.pravatar.cc/300'),
    (21, 'https://i.pravatar.cc/300'), (21, 'https://i.pravatar.cc/300'),
    (22, 'https://i.pravatar.cc/300'), (22, 'https://i.pravatar.cc/300'),
    (23, 'https://i.pravatar.cc/300'),
    (24, 'https://i.pravatar.cc/300'),
    (25, 'https://i.pravatar.cc/300'), (25, 'https://i.pravatar.cc/300'),
    (26, 'https://i.pravatar.cc/300'),
    (27, 'https://i.pravatar.cc/300'), (27, 'https://i.pravatar.cc/300'),
    (28, 'https://i.pravatar.cc/300'),
    (29, 'https://i.pravatar.cc/300'), (29, 'https://i.pravatar.cc/300'),
    (30, 'https://i.pravatar.cc/300'), (30, 'https://i.pravatar.cc/300');

-- =============================================
-- 9. FAVORITES
-- =============================================
INSERT INTO favorites (member_id, post_id)
VALUES
    (5, 1), (5, 4), (5, 16),
    (3, 16), (3, 18), (3, 22),
    (2, 3), (2, 4), (2, 23),
    (6, 6), (6, 9), (6, 21),
    (7, 7), (7, 10), (7, 20),
    (8, 8), (8, 12), (8, 25),
    (9, 9), (9, 13), (9, 22),
    (10, 6), (10, 7), (10, 24),
    (11, 5), (11, 11), (11, 19),
    (12, 8), (12, 11), (12, 26);

-- =============================================
-- 10. COMMENTS (rating 0-5, status: APPROVED/PENDING/REJECTED)
-- =============================================
INSERT INTO comments (post_id, member_id, rating, comment, status, created_at)
VALUES
    (1, 5, 5, N'Xe đẹp quá! Pin 42 kWh chạy xa không?', 'APPROVED', '2024-03-11 14:30:00'),
    (1, 3, 4, N'Giá có thương lượng không bạn?', 'APPROVED', '2024-03-11 16:45:00'),
    (1, 14, 5, N'VinFast VF e34 màu này cực đẹp!', 'APPROVED', '2024-03-12 09:15:00'),
    (1, 6, 4, N'15,000km thôi thì xe còn mới!', 'PENDING', '2024-03-12 13:20:00'),

    (2, 2, 5, N'Tesla Model 3 còn không bạn?', 'APPROVED', '2024-03-13 10:20:00'),
    (2, 10, 4, N'Pin 60 kWh còn bao nhiêu %?', 'APPROVED', '2024-03-13 15:30:00'),
    (2, 7, 3, N'Autopilot hoạt động tốt không?', 'REJECTED', '2024-03-14 08:45:00'),

    (16, 5, 5, N'Pin LG dùng cho xe gì được ạ?', 'APPROVED', '2024-03-16 09:15:00'),
    (16, 2, 5, N'Pin LG 60V 28Ah chất lượng tốt!', 'APPROVED', '2024-03-16 14:20:00'),
    (16, 8, 4, N'Giá 12 triệu là hợp lý rồi', 'APPROVED', '2024-03-17 10:30:00'),

    (4, 7, 4, N'VF 8 pin 87.7 kWh chạy xa nhỉ?', 'APPROVED', '2024-03-23 10:20:00'),
    (4, 13, 5, N'Xe cao cấp thì yên tâm hơn!', 'APPROVED', '2024-03-23 14:35:00'),
    (4, 11, 2, N'Màu xám bạc đẹp và bền màu!', 'PENDING', '2024-03-24 09:00:00'),

    (5, 8, 5, N'Nissan Leaf giá này quá hời!', 'APPROVED', '2024-03-24 15:30:00'),
    (5, 15, 5, N'Pin 40 kWh đủ dùng thành phố!', 'APPROVED', '2024-03-25 08:15:00'),
    (5, 4, 0, N'Xe nhập khẩu Nhật tin tưởng!', 'REJECTED', '2024-03-25 11:40:00'),

    (18, 6, 5, N'Pin Samsung chất lượng thế nào?', 'APPROVED', '2024-03-26 09:45:00'),
    (18, 12, 5, N'Pin Samsung rất bền!', 'APPROVED', '2024-03-26 14:20:00'),
    (18, 9, 4, N'60V 32Ah dung lượng khá lớn!', 'PENDING', '2024-03-27 10:15:00'),

    (6, 10, 5, N'BMW i3 đẹp quá! Pin ổn không?', 'APPROVED', '2024-03-28 14:15:00'),
    (6, 6, 5, N'Xe Đức nhập khẩu chất lượng!', 'APPROVED', '2024-03-29 09:30:00'),
    (6, 5, 3, N'780 triệu thì hơi đắt!', 'APPROVED', '2024-03-29 13:45:00'),

    (8, 13, 4, N'VF 9 pin 123 kWh chạy xa khủng!', 'APPROVED', '2024-03-31 10:40:00'),
    (8, 3, 5, N'Xe 7 chỗ sporty cực đẹp!', 'APPROVED', '2024-04-01 08:20:00'),
    (8, 9, 1, N'VF 9 êm ái và rộng rãi lắm!', 'REJECTED', '2024-04-01 13:15:00');

-- =============================================
-- 11. TRANSACTIONS (35 transactions)
-- =============================================
INSERT INTO transactions (buyer_id, post_id, status, created_at)
VALUES
    -- COMPLETED (10)
    (5, 1, 'COMPLETED', CAST('2024-03-25 10:00:00' AS DATETIME)),
    (6, 18, 'COMPLETED', CAST('2024-03-30 15:25:00' AS DATETIME)),
    (8, 11, 'COMPLETED', CAST('2024-04-09 16:45:00' AS DATETIME)),
    (2, 16, 'COMPLETED', CAST('2024-03-16 10:00:00' AS DATETIME)),
    (9, 21, 'COMPLETED', CAST('2024-04-05 14:20:00' AS DATETIME)),
    (11, 22, 'COMPLETED', CAST('2024-04-08 09:30:00' AS DATETIME)),
    (13, 4, 'COMPLETED', CAST('2024-03-23 15:45:00' AS DATETIME)),
    (6, 5, 'COMPLETED', CAST('2024-03-27 11:10:00' AS DATETIME)),
    (12, 19, 'COMPLETED', CAST('2024-04-03 16:30:00' AS DATETIME)),
    (14, 14, 'COMPLETED', CAST('2024-04-11 10:20:00' AS DATETIME)),

    -- DELIVERED (5)
    (7, 4, 'DELIVERED', CAST('2024-03-28 11:15:00' AS DATETIME)),
    (14, 9, 'DELIVERED', CAST('2024-04-05 12:20:00' AS DATETIME)),
    (15, 5, 'DELIVERED', CAST('2024-03-25 08:30:00' AS DATETIME)),
    (3, 8, 'DELIVERED', CAST('2024-03-31 14:15:00' AS DATETIME)),
    (10, 21, 'DELIVERED', CAST('2024-04-05 10:00:00' AS DATETIME)),

    -- PAID (5)
    (10, 6, 'PAID', CAST('2024-04-01 09:50:00' AS DATETIME)),
    (15, 29, 'PAID', CAST('2024-04-06 15:55:00' AS DATETIME)),
    (4, 22, 'PAID', CAST('2024-04-08 11:20:00' AS DATETIME)),
    (12, 15, 'PAID', CAST('2024-04-06 13:40:00' AS DATETIME)),
    (6, 25, 'PAID', CAST('2024-03-16 16:30:00' AS DATETIME)),

    -- ACCEPTED (5)
    (8, 5, 'ACCEPTED', CAST('2024-03-29 13:40:00' AS DATETIME)),
    (13, 8, 'ACCEPTED', CAST('2024-04-04 10:45:00' AS DATETIME)),
    (14, 1, 'ACCEPTED', CAST('2024-03-11 09:20:00' AS DATETIME)),
    (7, 9, 'ACCEPTED', CAST('2024-04-02 15:30:00' AS DATETIME)),
    (11, 6, 'ACCEPTED', CAST('2024-03-23 11:00:00' AS DATETIME)),

    -- REQUESTED (5)
    (2, 2, 'REQUESTED', CAST('2024-03-26 14:30:00' AS DATETIME)),
    (11, 10, 'REQUESTED', CAST('2024-04-02 14:15:00' AS DATETIME)),
    (7, 23, 'REQUESTED', CAST('2024-04-08 13:35:00' AS DATETIME)),
    (9, 27, 'REQUESTED', CAST('2024-03-31 08:45:00' AS DATETIME)),
    (15, 30, 'REQUESTED', CAST('2024-04-08 16:20:00' AS DATETIME)),

    -- CANCELLED (5)
    (10, 20, 'CANCELLED', CAST('2024-04-03 09:00:00' AS DATETIME)),
    (5, 6, 'CANCELLED', CAST('2024-03-28 10:30:00' AS DATETIME)),
    (8, 9, 'CANCELLED', CAST('2024-04-02 11:45:00' AS DATETIME)),
    (12, 7, 'CANCELLED', CAST('2024-03-25 14:20:00' AS DATETIME)),
    (14, 12, 'CANCELLED', CAST('2024-04-06 16:00:00' AS DATETIME));

-- =============================================
-- 12. CONTRACTS (MỖI TRANSACTION CÓ 1 CONTRACT - 35 contracts)
-- =============================================
INSERT INTO contracts (transaction_id, contract_url, signed_at, status, created_at)
VALUES
    -- COMPLETED transactions (1-10)
    (1, 'https://i.pravatar.cc/300', CAST('2024-03-25 11:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-25 10:30:00' AS DATETIME)),
    (2, 'https://i.pravatar.cc/300', CAST('2024-03-30 16:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-30 15:45:00' AS DATETIME)),
    (3, 'https://i.pravatar.cc/300', CAST('2024-04-09 17:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-09 17:00:00' AS DATETIME)),
    (4, 'https://i.pravatar.cc/300', CAST('2024-03-16 11:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-16 10:30:00' AS DATETIME)),
    (5, 'https://i.pravatar.cc/300', CAST('2024-04-05 15:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-05 14:45:00' AS DATETIME)),
    (6, 'https://i.pravatar.cc/300', CAST('2024-04-08 10:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-08 10:00:00' AS DATETIME)),
    (7, 'https://i.pravatar.cc/300', CAST('2024-03-23 16:30:00' AS DATETIME), 'SIGNED', CAST('2024-03-23 16:00:00' AS DATETIME)),
    (8, 'https://i.pravatar.cc/300', CAST('2024-03-27 12:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-27 11:30:00' AS DATETIME)),
    (9, 'https://i.pravatar.cc/300', CAST('2024-04-03 17:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-03 17:00:00' AS DATETIME)),
    (10, 'https://i.pravatar.cc/300', CAST('2024-04-11 11:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-11 10:45:00' AS DATETIME)),

    -- DELIVERED transactions (11-15)
    (11, 'https://i.pravatar.cc/300', CAST('2024-03-28 12:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-28 11:30:00' AS DATETIME)),
    (12, 'https://i.pravatar.cc/300', CAST('2024-04-05 13:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-05 12:45:00' AS DATETIME)),
    (13, 'https://i.pravatar.cc/300', CAST('2024-03-25 09:15:00' AS DATETIME), 'SIGNED', CAST('2024-03-25 09:00:00' AS DATETIME)),
    (14, 'https://i.pravatar.cc/300', CAST('2024-03-31 15:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-31 14:45:00' AS DATETIME)),
    (15, 'https://i.pravatar.cc/300', CAST('2024-04-05 10:45:00' AS DATETIME), 'SIGNED', CAST('2024-04-05 10:30:00' AS DATETIME)),

    -- PAID transactions (16-20)
    (16, 'https://i.pravatar.cc/300', CAST('2024-04-01 10:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-01 10:15:00' AS DATETIME)),
    (17, 'https://i.pravatar.cc/300', CAST('2024-04-06 16:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-06 16:15:00' AS DATETIME)),
    (18, 'https://i.pravatar.cc/300', CAST('2024-04-08 12:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-08 11:45:00' AS DATETIME)),
    (19, 'https://i.pravatar.cc/300', CAST('2024-04-06 14:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-06 14:00:00' AS DATETIME)),
    (20, 'https://i.pravatar.cc/300', CAST('2024-03-16 17:15:00' AS DATETIME), 'SIGNED', CAST('2024-03-16 17:00:00' AS DATETIME)),

    -- ACCEPTED transactions (21-25)
    (21, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-29 14:00:00' AS DATETIME)),
    (22, 'https://i.pravatar.cc/300', CAST('2024-04-04 11:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-04 11:00:00' AS DATETIME)),
    (23, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-11 09:45:00' AS DATETIME)),
    (24, 'https://i.pravatar.cc/300', CAST('2024-04-02 16:00:00' AS DATETIME), 'SIGNED', CAST('2024-04-02 15:45:00' AS DATETIME)),
    (25, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-23 11:30:00' AS DATETIME)),

    -- REQUESTED transactions (26-30)
    (26, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-26 15:00:00' AS DATETIME)),
    (27, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-04-02 14:45:00' AS DATETIME)),
    (28, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-04-08 14:00:00' AS DATETIME)),
    (29, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-31 09:15:00' AS DATETIME)),
    (30, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-04-08 16:45:00' AS DATETIME)),

    -- CANCELLED transactions (31-35)
    (31, 'https://i.pravatar.cc/300', CAST('2024-04-03 09:30:00' AS DATETIME), 'SIGNED', CAST('2024-04-03 09:15:00' AS DATETIME)),
    (32, 'https://i.pravatar.cc/300', CAST('2024-03-28 11:00:00' AS DATETIME), 'SIGNED', CAST('2024-03-28 10:45:00' AS DATETIME)),
    (33, 'https://i.pravatar.cc/300', CAST('2024-04-02 12:15:00' AS DATETIME), 'SIGNED', CAST('2024-04-02 12:00:00' AS DATETIME)),
    (34, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-03-25 14:45:00' AS DATETIME)),
    (35, 'https://i.pravatar.cc/300', NULL, 'UNSIGN', CAST('2024-04-06 16:30:00' AS DATETIME));

-- =============================================
-- 13. COMMISSIONS (MỖI TRANSACTION CÓ 1 COMMISSION - 35 commissions)
-- Quy tắc: BATTERY >10M=3% else 2%, VEHICLE >20M=4% else 2.5%
-- =============================================
INSERT INTO commissions (transaction_id, commission_rate, amount, status, created_at)
VALUES
    -- Transaction 1: Post 1 (VEHICLE, 650M) -> 4%
    (1, 0.04, 26000000.00, 'PAID', CAST('2024-03-25 12:00:00' AS DATETIME)),
    -- Transaction 2: Post 18 (BATTERY, 15M) -> 3%
    (2, 0.03, 450000.00, 'PAID', CAST('2024-03-30 17:00:00' AS DATETIME)),
    -- Transaction 3: Post 11 (VEHICLE, 2500M) -> 4%
    (3, 0.04, 100000000.00, 'PAID', CAST('2024-04-09 18:00:00' AS DATETIME)),
    -- Transaction 4: Post 16 (BATTERY, 12M) -> 3%
    (4, 0.03, 360000.00, 'PAID', CAST('2024-03-16 12:00:00' AS DATETIME)),
    -- Transaction 5: Post 21 (BATTERY, 20M) -> 3%
    -- Transaction 12: Post 9 (VEHICLE, 2200M) -> 4%
    (12, 0.04, 88000000.00, 'PAID', CAST('2024-04-05 14:00:00' AS DATETIME)),
    -- Transaction 13: Post 5 (VEHICLE, 480M) -> 4%
    (13, 0.04, 19200000.00, 'PAID', CAST('2024-03-25 10:00:00' AS DATETIME)),
    -- Transaction 14: Post 8 (VEHICLE, 1550M) -> 4%
    (14, 0.04, 62000000.00, 'PAID', CAST('2024-03-31 16:00:00' AS DATETIME)),
    -- Transaction 15: Post 21 (BATTERY, 20M) -> 3%
    (15, 0.03, 600000.00, 'PAID', CAST('2024-04-05 11:30:00' AS DATETIME)),

    -- Transaction 16: Post 6 (VEHICLE, 780M) -> 4%
    (16, 0.04, 31200000.00, 'PAID', CAST('2024-04-01 11:00:00' AS DATETIME)),
    -- Transaction 17: Post 29 (BATTERY, 14M) -> 3%
    (17, 0.03, 420000.00, 'PAID', CAST('2024-04-06 17:00:00' AS DATETIME)),
    -- Transaction 18: Post 22 (BATTERY, 11M) -> 3%
    (18, 0.03, 330000.00, 'PAID', CAST('2024-04-08 13:00:00' AS DATETIME)),
    -- Transaction 19: Post 15 (VEHICLE, 1280M) -> 4%
    (19, 0.04, 51200000.00, 'PAID', CAST('2024-04-06 15:00:00' AS DATETIME)),
    -- Transaction 20: Post 25 (BATTERY, 13M) -> 3%
    (20, 0.03, 390000.00, 'PAID', CAST('2024-03-16 18:00:00' AS DATETIME)),

    -- Transaction 21: Post 5 (VEHICLE, 480M) -> 4%
    (21, 0.04, 19200000.00, 'PAID', CAST('2024-03-29 15:00:00' AS DATETIME)),
    -- Transaction 22: Post 8 (VEHICLE, 1550M) -> 4%
    (22, 0.04, 62000000.00, 'PAID', CAST('2024-04-04 12:00:00' AS DATETIME)),
    -- Transaction 23: Post 1 (VEHICLE, 650M) -> 4%
    (23, 0.04, 26000000.00, 'PAID', CAST('2024-03-11 10:30:00' AS DATETIME)),
    -- Transaction 24: Post 9 (VEHICLE, 2200M) -> 4%
    (24, 0.04, 88000000.00, 'PAID', CAST('2024-04-02 17:00:00' AS DATETIME)),
    -- Transaction 25: Post 6 (VEHICLE, 780M) -> 4%
    (25, 0.04, 31200000.00, 'PAID', CAST('2024-03-23 12:00:00' AS DATETIME)),

    -- Transaction 26: Post 2 (VEHICLE, 1250M) -> 4%
    (26, 0.04, 50000000.00, 'PAID', CAST('2024-03-26 16:00:00' AS DATETIME)),
    -- Transaction 27: Post 10 (VEHICLE, 1350M) -> 4%
    (27, 0.04, 54000000.00, 'PAID', CAST('2024-04-02 16:00:00' AS DATETIME)),
    -- Transaction 28: Post 23 (BATTERY, 9M) -> 2%
    (28, 0.02, 180000.00, 'PAID', CAST('2024-04-08 15:00:00' AS DATETIME)),
    -- Transaction 29: Post 27 (BATTERY, 850K) -> 2%
    (29, 0.02, 17000.00, 'PAID', CAST('2024-03-31 10:30:00' AS DATETIME)),
    -- Transaction 30: Post 30 (BATTERY, 16M) -> 3%
    (30, 0.03, 480000.00, 'PAID', CAST('2024-04-08 18:00:00' AS DATETIME)),

    -- Transaction 31: Post 20 (BATTERY, 7M) -> 2%
    (31, 0.02, 140000.00, 'PAID', CAST('2024-04-03 10:30:00' AS DATETIME)),
    -- Transaction 32: Post 6 (VEHICLE, 780M) -> 4%
    (32, 0.04, 31200000.00, 'PAID', CAST('2024-03-28 12:00:00' AS DATETIME)),
    -- Transaction 33: Post 9 (VEHICLE, 2200M) -> 4%
    (33, 0.04, 88000000.00, 'PAID', CAST('2024-04-02 13:00:00' AS DATETIME)),
    -- Transaction 34: Post 7 (VEHICLE, 550M) -> 4%
    (34, 0.04, 22000000.00, 'PAID', CAST('2024-03-25 15:30:00' AS DATETIME)),
    -- Transaction 35: Post 12 (VEHICLE, 680M) -> 4%
    (35, 0.04, 27200000.00, 'PAID', CAST('2024-04-06 17:30:00' AS DATETIME));
-- =============================================
-- 14. REVIEWS (rating 0-5, status: APPROVED/PENDING/REJECTED)
-- Chỉ review cho các transactions COMPLETED
-- =============================================
INSERT INTO reviews (seller_id, reviewer_id, transaction_id, rating, comment, status, created_at)
VALUES
    -- Transaction 1: Post 1, seller=2, buyer=5
    (2, 5, 1, 5, N'Seller chuyên nghiệp, VF e34 đẹp như mô tả, pin 42 kWh chạy xa!', 'APPROVED', CAST('2024-03-27 14:20:00' AS DATETIME)),
    -- Transaction 2: Post 18, seller=8, buyer=6
    (8, 6, 2, 5, N'Pin Samsung SDI 60V 32Ah chất lượng cao, rất hài lòng.', 'APPROVED', CAST('2024-03-31 16:20:00' AS DATETIME)),
    -- Transaction 3: Post 11, seller=7, buyer=8
    (7, 8, 3, 4, N'Mercedes EQC pin 80 kWh máy ổn. Seller uy tín!', 'APPROVED', CAST('2024-04-10 09:25:00' AS DATETIME)),
    -- Transaction 4: Post 16, seller=4, buyer=2
    (4, 2, 4, 5, N'Pin LG 60V 28Ah chính hãng, bảo hành tốt!', 'APPROVED', CAST('2024-03-17 11:30:00' AS DATETIME)),
    -- Transaction 5: Post 21, seller=6, buyer=9
    (6, 9, 5, 4, N'Pin VinFast 72V 28Ah chất lượng, đáng giá!', 'APPROVED', CAST('2024-04-06 15:45:00' AS DATETIME)),
    -- Transaction 6: Post 22, seller=9, buyer=11
    (9, 11, 6, 5, N'Pin Bosch 48V 25Ah chạy xa, giao hàng nhanh!', 'APPROVED', CAST('2024-04-09 10:15:00' AS DATETIME)),
    -- Transaction 7: Post 4, seller=6, buyer=13
    (6, 13, 7, 4, N'VinFast VF 8 pin 87.7 kWh ổn định, giá tốt!', 'PENDING', CAST('2024-03-24 16:30:00' AS DATETIME)),
    -- Transaction 8: Post 5, seller=7, buyer=6
    (7, 6, 8, 5, N'Nissan Leaf pin 40 kWh đẹp như mới, hài lòng!', 'APPROVED', CAST('2024-03-28 13:40:00' AS DATETIME)),
    -- Transaction 9: Post 19, seller=11, buyer=12
    (11, 12, 9, 3, N'Pin CATL 72V dung lượng lớn, giao đúng hẹn.', 'REJECTED', CAST('2024-04-04 17:45:00' AS DATETIME)),
    -- Transaction 10: Post 14, seller=4, buyer=14
    (4, 14, 10, 4, N'Porsche Taycan pin 93.4 kWh chạy tốt, giá hợp lý!', 'APPROVED', CAST('2024-04-12 11:20:00' AS DATETIME));

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

-- =============================================
-- SUMMARY
-- =============================================
-- 15 Members (status: ACTIVE, SUSPENDED, BANNED)
-- 3 Membership Plans (Free, Basic, Premium)
-- 15 Vehicles (battery_capacity: số + kWh)
-- 15 Batteries
-- 30 Products (15 VEHICLE + 15 BATTERY, mỗi product chỉ có 1 vehicle HOẶC 1 battery)
-- 30 Posts (15 VEHICLE + 15 BATTERY, status: APPROVED, PENDING, REJECTED)
-- 60+ Post Images (mỗi post có ít nhất 1 hình)
-- 15 Member Plan Usage (mỗi member có 1 plan)
-- 30 Favorites
-- 25 Comments (rating: 0-5, status: APPROVED, PENDING, REJECTED)
-- 35 Transactions (REQUESTED -> ACCEPTED -> PAID -> DELIVERED -> COMPLETED / CANCELLED)
-- 35 Contracts (MỖI TRANSACTION CÓ 1 CONTRACT, status: SIGNED / UNSIGN)
-- 35 Commissions (MỖI TRANSACTION CÓ 1 COMMISSION, BATTERY >10M=3% else 2%, VEHICLE >20M=4% else 2.5%)
-- 10 Reviews (chỉ cho transactions COMPLETED, rating: 0-5)