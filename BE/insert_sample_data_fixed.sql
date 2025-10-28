-- =============================================
-- SQL INSERT SCRIPT FOR CARRY DATABASE
-- Generated based on Entity relationships
-- Microsoft SQL Server (T-SQL) Version
-- =============================================



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
