// Create Keyspace query
CREATE KEYSPACE bookings WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'} AND durable_writes = true;

// Create table query
CREATE TABLE bookings.bookings (
  bookingref text PRIMARY KEY,
  container_size int,
  container_type text,
  destination text,
  origin text,
  quantity int,
  timestamp text
)