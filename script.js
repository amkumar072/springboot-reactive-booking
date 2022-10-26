CREATE TABLE bookings.bookings (
  bookingref text PRIMARY KEY,
  container_size int,
  container_type text,
  destination text,
  origin text,
  quantity int,
  timestamp text
)