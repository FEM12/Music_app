package com.example.music_app.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.music_app.model.Album
import com.example.music_app.model.Artist
import com.example.music_app.model.Song

class DBHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "music.db"
        private const val DATABASE_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
                PRAGMA foreign_keys = ON;
            """.trimIndent()
        )

        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS artists (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  name VARCHAR(50) NOT NULL,
                  stature REAL NOT NULL,
                  nationality VARCHAR(100) NOT NULL,
                  birthdate TEXT NOT NULL
                );
            """.trimIndent()
        )

        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS albums(
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  artist_id INTEGER NOT NULL,
                  name VARCHAR(50) NOT NULL,
                  publication_date TEXT NOT NULL,
                  discography VARCHAR(50) NOT NULL,
                  FOREIGN KEY (artist_id) REFERENCES artists(id) ON DELETE CASCADE
                );
            """.trimIndent()
        )

        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS songs(
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  album_id INTEGER NOT NULL,
                  name VARCHAR(50) NOT NULL,
                  publication_date TEXT NOT NULL,
                  duration TEXT NOT NULL,
                  genre VARCHAR(100) NOT NULL,
                  FOREIGN KEY (album_id) REFERENCES albums(id) ON DELETE CASCADE
                );
            """.trimIndent()
        )

        insertInitialData(db)

    }

    private fun insertInitialData(db: SQLiteDatabase) {

        db.execSQL(
            """
                INSERT INTO artists (name, stature, nationality, birthdate) VALUES
                ('Freddie Mercury', 1.77, 'British', '1946-09-05'),
                ('David Bowie', 1.78, 'British', '1947-01-08'),
                ('Aretha Franklin', 1.65, 'American', '1942-03-25'),
                ('Elton John', 1.73, 'British', '1947-03-25'),
                ('Stevie Wonder', 1.65, 'American', '1950-05-13'),
                ('Madonna', 1.63, 'American', '1958-08-16'),
                ('Prince', 1.57, 'American', '1958-06-07'),
                ('Bruce Springsteen', 1.71, 'American', '1949-09-23'),
                ('Bjork', 1.71, 'Icelandic', '1965-11-21'),
                ('Nina Simone', 1.73, 'American', '1933-02-21');

            """.trimIndent()
        )

        db.execSQL(
            """
                INSERT INTO albums (artist_id, name, publication_date, discography) VALUES                
                (1, 'Freddie Mercury Debut', '1975-06-01', 'Queen Records'),
                (1, 'Mercury Rising', '1976-08-12', 'Queen Records'),
                (1, 'Bohemian Nights', '1977-09-05', 'Queen Records'),
                (1, 'Vocal Mastery', '1978-11-20', 'Queen Records'),
                (1, 'Starlight Symphony', '1979-03-15', 'Queen Records'),
                (1, 'Echoes of Mercury', '1980-05-25', 'Queen Records'),
                (1, 'Golden Voice', '1981-07-30', 'Queen Records'),
                (1, 'Live Forever', '1982-10-12', 'Queen Records'),
                (1, 'Queen´s Charm', '1983-12-01', 'Queen Records'),
                (1, 'Final Note', '1984-02-18', 'Queen Records'),
                (2, 'Ziggy Begins', '1971-03-15', 'Bowie Records'),
                (2, 'Starman Tales', '1972-06-20', 'Bowie Records'),
                (2, 'Moonlight Dance', '1973-09-10', 'Bowie Records'),
                (2, 'Chameleon Dreams', '1974-11-05', 'Bowie Records'),
                (2, 'Space Oddities', '1975-02-28', 'Bowie Records'),
                (2, 'Glamour Nights', '1976-07-12', 'Bowie Records'),
                (2, 'Labyrinth Mind', '1977-09-25', 'Bowie Records'),
                (2, 'Heroes Echo', '1978-12-10', 'Bowie Records'),
                (2, 'Diamond Dog', '1979-04-01', 'Bowie Records'),
                (2, 'Young Americans', '1980-06-20', 'Bowie Records'),
                (3, 'Soul Queen', '1965-01-10', 'Atlantic Records'),
                (3, 'Respect', '1966-03-20', 'Atlantic Records'),
                (3, 'Natural Woman', '1967-06-15', 'Atlantic Records'),
                (3, 'Chain Reaction', '1968-09-01', 'Atlantic Records'),
                (3, 'Amazing Grace', '1969-11-12', 'Atlantic Records'),
                (3, 'Spirit of Soul', '1970-02-28', 'Atlantic Records'),
                (3, 'Queen´s Voice', '1971-05-05', 'Atlantic Records'),
                (3, 'Soulful Nights', '1972-08-20', 'Atlantic Records'),
                (3, 'Unforgettable', '1973-10-30', 'Atlantic Records'),
                (3, 'Legacy of Soul', '1974-12-15', 'Atlantic Records'),
                (4, 'Empty Sky', '1970-03-01', 'Rocket Records'),
                (4, 'Elton´s Garden', '1971-06-10', 'Rocket Records'),
                (4, 'Goodbye Yellow Brick', '1972-09-20', 'Rocket Records'),
                (4, 'Rocket Man', '1973-11-05', 'Rocket Records'),
                (4, 'Candle in the Wind', '1974-02-28', 'Rocket Records'),
                (4, 'Tiny Dancer', '1975-05-12', 'Rocket Records'),
                (4, 'Captain Fantastic', '1976-08-25', 'Rocket Records'),
                (4, 'Blue Moves', '1977-10-30', 'Rocket Records'),
                (4, 'Too Low for Zero', '1978-01-15', 'Rocket Records'),
                (4, 'Victim of Love', '1979-03-20', 'Rocket Records'),
                (5, 'Fingertips', '1965-05-01', 'Tamla Records'),
                (5, 'Uptight', '1966-07-15', 'Tamla Records'),
                (5, 'Signed, Sealed', '1967-09-20', 'Tamla Records'),
                (5, 'Songs in the Key', '1968-11-25', 'Tamla Records'),
                (5, 'Innervisions', '1969-02-10', 'Tamla Records'),
                (5, 'Talking Book', '1970-05-20', 'Tamla Records'),
                (5, 'Hotter Than July', '1971-08-30', 'Tamla Records'),
                (5, 'Fulfillingness', '1972-10-12', 'Tamla Records'),
                (5, 'Journey Through', '1973-01-05', 'Tamla Records'),
                (5, 'Music of My Mind', '1974-03-18', 'Tamla Records'),
                (6, 'Madonna', '1983-07-27', 'Sire Records'),
                (6, 'Like a Virgin', '1984-11-12', 'Sire Records'),
                (6, 'True Blue', '1986-06-30', 'Sire Records'),
                (6, 'Like a Prayer', '1989-03-21', 'Sire Records'),
                (6, 'Erotica', '1992-10-20', 'Sire Records'),
                (6, 'Bedtime Stories', '1994-03-28', 'Sire Records'),
                (6, 'Ray of Light', '1998-02-22', 'Warner Records'),
                (6, 'Music', '2000-09-18', 'Warner Records'),
                (6, 'American Life', '2003-04-08', 'Warner Records'),
                (6, 'Confessions', '2005-05-14', 'Warner Records'),                
                (7, 'For You', '1978-04-07', 'Warner Bros.'),
                (7, 'Prince', '1979-10-19', 'Warner Bros.'),
                (7, 'Dirty Mind', '1980-10-08', 'Warner Bros.'),
                (7, 'Controversy', '1981-10-14', 'Warner Bros.'),
                (7, '1999', '1982-10-27', 'Warner Bros.'),
                (7, 'Purple Rain', '1984-06-25', 'Warner Bros.'),
                (7, 'Around the World', '1985-11-15', 'Warner Bros.'),
                (7, 'Parade', '1986-03-31', 'Warner Bros.'),
                (7, 'Sign o´ the Times', '1987-03-30', 'Warner Bros.'),
                (7, 'Lovesexy', '1988-05-10', 'Warner Bros.'),
                (8, 'Greetings', '1973-01-05', 'Columbia Records'),
                (8, 'The Wild', '1975-05-15', 'Columbia Records'),
                (8, 'Born to Run', '1975-08-25', 'Columbia Records'),
                (8, 'Darkness', '1978-06-02', 'Columbia Records'),
                (8, 'The River', '1980-10-17', 'Columbia Records'),
                (8, 'Nebraska', '1982-09-30', 'Columbia Records'),
                (8, 'Born in the USA', '1984-06-04', 'Columbia Records'),
                (8, 'Tunnel of Love', '1987-10-09', 'Columbia Records'),
                (8, 'Human Touch', '1992-03-31', 'Columbia Records'),
                (8, 'Lucky Town', '1992-03-31', 'Columbia Records'),
                (9, 'Debut', '1993-07-05', 'One Little Indian'),
                (9, 'Post', '1995-06-13', 'One Little Indian'),
                (9, 'Homogenic', '1997-09-22', 'One Little Indian'),
                (9, 'Vespertine', '2001-08-27', 'One Little Indian'),
                (9, 'Medúlla', '2004-08-30', 'One Little Indian'),
                (9, 'Volta', '2007-05-01', 'One Little Indian'),
                (9, 'Biophilia', '2011-10-05', 'One Little Indian'),
                (9, 'Vulnicura', '2015-01-20', 'One Little Indian'),
                (9, 'Utopia', '2017-11-24', 'One Little Indian'),
                (9, 'Fossora', '2022-09-30', 'One Little Indian'),
                (10, 'Little Girl Blue', '1958-02-01', 'Bethlehem Records'),
                (10, 'The Amazing Nina Simone', '1959-06-10', 'Colpix Records'),
                (10, 'Nina at the Village Gate', '1962-05-03', 'Colpix Records'),
                (10, 'Forbidden Fruit', '1961-06-01', 'Colpix Records'),
                (10, 'Wild Is the Wind', '1966-12-29', 'Philips Records'),
                (10, 'Silk & Soul', '1967-09-04', 'Philips Records'),
                (10, 'Nuff Said!', '1968-05-20', 'RCA Records'),
                (10, 'To Love Somebody', '1969-06-12', 'RCA Records'),
                (10, 'Black Gold', '1970-09-30', 'RCA Records'),
                (10, 'Emergency Ward!', '1972-05-15', 'RCA Records');

            """.trimIndent()
        )

        db.execSQL(
            """
                INSERT INTO songs (album_id, name, publication_date, duration, genre) VALUES
                (1, 'Opening Fanfare', '1975-06-01', '3:15', 'Glam Rock'),
                (1, 'The Singer´s First Bow', '1975-06-01', '4:40', 'Hard Rock'),
                (1, 'A Night at the Opera Prelude', '1975-06-01', '2:55', 'Progressive Rock'),
                (1, 'Diamond Tears', '1975-06-01', '5:01', 'Power Ballad'),
                (1, 'New Star on the Horizon', '1975-06-01', '3:22', 'Rock'),
                (2, 'Hot Air Balloon', '1976-08-12', '4:05', 'Art Rock'),
                (2, 'The Voice of ´76', '1976-08-12', '3:18', 'Arena Rock'),
                (2, 'Melody of a King', '1976-08-12', '5:10', 'Piano Rock'),
                (2, 'Stadium Anthem', '1976-08-12', '3:45', 'Hard Rock'),
                (2, 'Crescendo', '1976-08-12', '4:50', 'Progressive Pop'),
                (3, 'Carnival of Souls', '1977-09-05', '6:02', 'Glam Rock'),
                (3, 'Midnight Serenade', '1977-09-05', '3:35', 'Ballad'),
                (3, 'Waltz of the Moon King', '1977-09-05', '4:20', 'Operatic Rock'),
                (3, 'Masquerade', '1977-09-05', '2:45', 'Rock'),
                (3, 'Velvet Curtain Call', '1977-09-05', '5:12', 'Hard Rock'),
                (4, 'The Tenor´s Flight', '1978-11-20', '4:15', 'Pop Rock'),
                (4, 'Octave Jump', '1978-11-20', '3:50', 'Rock'),
                (4, 'Power Note', '1978-11-20', '5:30', 'Heavy Rock'),
                (4, 'Beyond the Range', '1978-11-20', '2:59', 'Ballad'),
                (4, 'Show Must Go On (Early Mix)', '1978-11-20', '4:08', 'Arena Rock'),
                (5, 'Cosmic Overture', '1979-03-15', '1:55', 'Instrumental'),
                (5, 'Astral Rendezvous', '1979-03-15', '4:30', 'Disco Rock'),
                (5, 'Galactic Glide', '1979-03-15', '3:40', 'Funk Rock'),
                (5, 'Nova Blast', '1979-03-15', '5:05', 'Space Rock'),
                (5, 'The Milky Way Bridge', '1979-03-15', '3:10', 'Synth Pop'),
                (6, 'Reverbation', '1980-05-25', '4:18', 'New Wave'),
                (6, 'The Ghost of Rock Past', '1980-05-25', '3:55', 'Pop Rock'),
                (6, 'Digital Love Song', '1980-05-25', '5:20', 'Synth Rock'),
                (6, 'Vintage Groove', '1980-05-25', '2:50', 'Funk'),
                (6, 'Distant Voice', '1980-05-25', '4:45', 'Ballad'),
                (7, 'The Midas Touch', '1981-07-30', '3:25', 'Pop Rock'),
                (7, 'Shining Performance', '1981-07-30', '4:10', 'Rock'),
                (7, 'Aria for the Crowd', '1981-07-30', '5:00', 'Power Ballad'),
                (7, 'Solid Gold Track', '1981-07-30', '3:48', 'Funk'),
                (7, 'Glittering Exit', '1981-07-30', '4:52', 'Arena Rock'),
                (8, 'Immortality Hymn', '1982-10-12', '4:22', 'Anthem Rock'),
                (8, 'The Final Curtain', '1982-10-12', '3:30', 'New Wave'),
                (8, 'Endless Encore', '1982-10-12', '5:15', 'Ballad'),
                (8, 'A Legacy Unbroken', '1982-10-12', '4:03', 'Pop Rock'),
                (8, 'Eternal Flame', '1982-10-12', '3:58', 'Synth Rock'),
                (9, 'Regal Entrance', '1983-12-01', '3:05', 'Pop'),
                (9, 'Royal Decree', '1983-12-01', '4:35', 'Synth Pop'),
                (9, 'The Crown Jewels', '1983-12-01', '5:08', 'Dance Pop'),
                (9, 'Majestic Groove', '1983-12-01', '3:42', 'Funk'),
                (9, 'Palace Lights', '1983-12-01', '4:19', 'Pop Rock'),
                (10, 'Last Call', '1984-02-18', '4:00', 'Ballad'),
                (10, 'The Farewell Song', '1984-02-18', '3:50', 'Pop Rock'),
                (10, 'Silence and Smoke', '1984-02-18', '5:10', 'Soft Rock'),
                (10, 'Echoes of the Stage', '1984-02-18', '3:28', 'Synth Pop'),
                (10, 'The Curtain Falls', '1984-02-18', '4:40', 'Power Ballad'),
                (11, 'Martian Messenger', '1971-03-15', '4:20', 'Glam Rock'),
                (11, 'Electric Zig', '1971-03-15', '3:15', 'Art Rock'),
                (11, 'Future Shock', '1971-03-15', '5:05', 'Proto-Punk'),
                (11, 'The Spiders´ First Web', '1971-03-15', '3:40', 'Hard Rock'),
                (11, 'Velvet Suit', '1971-03-15', '4:55', 'Ballad'),
                (12, 'Rocket Ride', '1972-06-20', '3:30', 'Glam Rock'),
                (12, 'Life on Mars II', '1972-06-20', '4:15', 'Art Pop'),
                (12, 'Space Oddity Follow Up', '1972-06-20', '5:20', 'Progressive Rock'),
                (12, 'The Alien´s Stroll', '1972-06-20', '2:55', 'Rock'),
                (12, 'Glitter and Dust', '1972-06-20', '4:45', 'Power Ballad'),
                (13, 'Silver Shoes', '1973-09-10', '4:00', 'Glam Rock'),
                (13, 'Shadow Man', '1973-09-10', '3:25', 'Art Rock'),
                (13, 'Late Night Cruising', '1973-09-10', '5:10', 'Soft Rock'),
                (13, 'Dancing in the Dark', '1973-09-10', '3:48', 'Pop Rock'),
                (13, 'Blue Glow', '1973-09-10', '4:59', 'Ballad'),
                (14, 'Quick Change', '1974-11-05', '4:30', 'Art Rock'),
                (14, 'Diamond in the Rough', '1974-11-05', '3:55', 'Soul'),
                (14, 'The Many Faces of You', '1974-11-05', '5:00', 'Funk Rock'),
                (14, 'Mirror Image', '1974-11-05', '3:12', 'Rock'),
                (14, 'Shattered Glass', '1974-11-05', '4:40', 'Experimental'),
                (15, 'Major Tom´s Return', '1975-02-28', '6:10', 'Progressive Rock'),
                (15, 'Stardust Boy', '1975-02-28', '3:50', 'Glam Rock'),
                (15, 'Zero Gravity Waltz', '1975-02-28', '4:25', 'Pop Rock'),
                (15, 'Lost in Orbit', '1975-02-28', '5:35', 'Ambient'),
                (15, 'Cosmic Dust', '1975-02-28', '3:05', 'Instrumental'),
                (16, 'Red Carpet Stomp', '1976-07-12', '4:08', 'Funk Rock'),
                (16, 'Hollywood Fade', '1976-07-12', '3:18', 'Blue-eyed Soul'),
                (16, 'The Thin White Duke Theme', '1976-07-12', '5:15', 'Art Pop'),
                (16, 'Chic Factory', '1976-07-12', '3:59', 'Disco'),
                (16, 'Gilded Cage', '1976-07-12', '4:42', 'Ballad'),
                (17, 'The Maze', '1977-09-25', '4:35', 'Art Rock'),
                (17, 'Berlin Walls', '1977-09-25', '5:05', 'Electronic'),
                (17, 'Subterranean Tunnels', '1977-09-25', '6:00', 'Ambient'),
                (17, 'The Puppet Master', '1977-09-25', '3:50', 'New Wave'),
                (17, 'Escape Route', '1977-09-25', '4:15', 'Experimental'),
                (18, 'Legacy', '1978-12-10', '4:40', 'New Wave'),
                (18, 'The Next Day', '1978-12-10', '3:30', 'Post-Punk'),
                (18, 'Sound and Vision II', '1978-12-10', '5:25', 'Electronic'),
                (18, 'Wall of Sound', '1978-12-10', '4:01', 'Art Rock'),
                (18, 'Symphony of the Brave', '1978-12-10', '3:55', 'Instrumental'),
                (19, 'Future is a Zoo', '1979-04-01', '4:05', 'Hard Rock'),
                (19, 'Rebel Rebel II', '1979-04-01', '3:20', 'Glam Rock'),
                (19, 'Street Fighting Man', '1979-04-01', '5:10', 'Proto-Punk'),
                (19, 'Peking Dog', '1979-04-01', '3:45', 'Art Rock'),
                (19, 'Aladdin´s Sane', '1979-04-01', '4:50', 'Rock'),
                (20, 'Fascination Street', '1980-06-20', '4:12', 'Blue-eyed Soul'),
                (20, 'Plastic Soul', '1980-06-20', '3:55', 'Philly Soul'),
                (20, 'Saccharine', '1980-06-20', '5:00', 'Funk'),
                (20, 'New York City Lights', '1980-06-20', '3:28', 'Pop'),
                (20, 'American Boy', '1980-06-20', '4:30', 'Soul'),
                (21, 'Royal Soul', '1965-01-10', '3:05', 'R&B'),
                (21, 'The Throne', '1965-01-10', '4:10', 'Gospel'),
                (21, 'Memphis Beat', '1965-01-10', '2:50', 'Soul'),
                (21, 'Crown on the Head', '1965-01-10', '3:35', 'Blues'),
                (21, 'A New Reign', '1965-01-10', '3:15', 'Jazz'),
                (22, 'R.E.S.P.E.C.T. II', '1966-03-20', '3:40', 'Soul'),
                (22, 'Demanding Attention', '1966-03-20', '2:55', 'R&B'),
                (22, 'Take It To Heart', '1966-03-20', '4:05', 'Gospel'),
                (22, 'The Power of a Woman', '1966-03-20', '3:20', 'Blues'),
                (22, 'Keep Movin´', '1966-03-20', '3:12', 'Pop Soul'),
                (23, 'The Earth Sings', '1967-06-15', '3:50', 'Soul'),
                (23, 'Felt Like Rain', '1967-06-15', '4:15', 'R&B'),
                (23, 'Born to Sing', '1967-06-15', '2:45', 'Gospel'),
                (23, 'Mirror Image', '1967-06-15', '3:25', 'Blues'),
                (23, 'Authentic Groove', '1967-06-15', '3:08', 'Pop Soul'),
                (24, 'Domino Effect', '1968-09-01', '3:35', 'Soul'),
                (24, 'One Link to the Next', '1968-09-01', '4:00', 'R&B'),
                (24, 'Explosion of Emotion', '1968-09-01', '2:58', 'Gospel'),
                (24, 'Connecting the Dots', '1968-09-01', '3:18', 'Blues'),
                (24, 'The Movement', '1968-09-01', '3:45', 'Pop Soul'),
                (25, 'Hallelujah Soul', '1969-11-12', '5:10', 'Gospel'),
                (25, 'Divine Love', '1969-11-12', '4:30', 'Soul'),
                (25, 'Precious Lord', '1969-11-12', '6:05', 'Traditional Gospel'),
                (25, 'Worship', '1969-11-12', '3:55', 'R&B'),
                (25, 'Amen (Live)', '1969-11-12', '4:50', 'Gospel'),
                (26, 'The Essence', '1970-02-28', '3:40', 'Soul'),
                (26, 'Inner Fire', '1970-02-28', '4:18', 'R&B'),
                (26, 'Feeling Good', '1970-02-28', '3:00', 'Jazz'),
                (26, 'Uplifted', '1970-02-28', '3:22', 'Gospel'),
                (26, 'Rhythm and Belief', '1970-02-28', '4:05', 'Pop Soul'),
                (27, 'Vocal Royalty', '1971-05-05', '3:55', 'Soul'),
                (27, 'The Scepter', '1971-05-05', '4:25', 'R&B'),
                (27, 'Echoes of a Diva', '1971-05-05', '3:10', 'Blues'),
                (27, 'The Proclamation', '1971-05-05', '3:30', 'Pop Soul'),
                (27, 'Majestic Sound', '1971-05-05', '4:08', 'Gospel'),
                (28, 'Late Groove', '1972-08-20', '4:10', 'Soul'),
                (28, 'Candlelight Serenade', '1972-08-20', '3:50', 'R&B'),
                (28, 'Midnight Rendezvous', '1972-08-20', '5:00', 'Jazz'),
                (28, 'Smooth Operator', '1972-08-20', '3:25', 'Pop Soul'),
                (28, 'Under the Stars', '1972-08-20', '4:35', 'Blues'),
                (29, 'Timeless', '1973-10-30', '4:20', 'Soul'),
                (29, 'Eternal Memory', '1973-10-30', '3:45', 'R&B'),
                (29, 'The Classic', '1973-10-30', '5:15', 'Jazz'),
                (29, 'Never Say Goodbye', '1973-10-30', '3:33', 'Pop Soul'),
                (29, 'Lasting Impression', '1973-10-30', '4:02', 'Ballad'),
                (30, 'The Ancestors´ Song', '1974-12-15', '4:30', 'Soul'),
                (30, 'Heirloom', '1974-12-15', '3:55', 'R&B'),
                (30, 'Generations', '1974-12-15', '5:05', 'Gospel'),
                (30, 'The Queen´s Will', '1974-12-15', '3:20', 'Jazz'),
                (30, 'Soul Survivor', '1974-12-15', '4:40', 'Pop Soul'),
                (31, 'Hollow Feeling', '1970-03-01', '4:05', 'Folk Rock'),
                (31, 'The Sun is Gone', '1970-03-01', '3:30', 'Pop Rock'),
                (31, 'Acoustic Sunrise', '1970-03-01', '5:15', 'Psychedelic Rock'),
                (31, 'Looking Up', '1970-03-01', '3:20', 'Ballad'),
                (31, 'Blue Piano', '1970-03-01', '4:40', 'Soft Rock'),
                (32, 'Rose Petals', '1971-06-10', '4:10', 'Pop Rock'),
                (32, 'Green Grass', '1971-06-10', '3:50', 'Country Rock'),
                (32, 'The Gardener´s Song', '1971-06-10', '5:00', 'Piano Rock'),
                (32, 'Watering Can', '1971-06-10', '3:05', 'Ballad'),
                (32, 'Floral Fantasy', '1971-06-10', '4:25', 'Folk Rock'),
                (33, 'The Road Less Traveled', '1972-09-20', '4:30', 'Pop Rock'),
                (33, 'Emerald City Blues', '1972-09-20', '3:55', 'Glam Rock'),
                (33, 'Wizard of Oz II', '1972-09-20', '5:20', 'Art Rock'),
                (33, 'Ruby Slippers', '1972-09-20', '3:15', 'Ballad'),
                (33, 'No Place Like Home', '1972-09-20', '4:48', 'Piano Rock'),
                (34, 'Space Traveler', '1973-11-05', '4:00', 'Pop Rock'),
                (34, 'Countdown', '1973-11-05', '3:25', 'Soft Rock'),
                (34, 'Astronaut´s Lament', '1973-11-05', '5:10', 'Ballad'),
                (34, 'Zero G Groove', '1973-11-05', '3:45', 'Glam Rock'),
                (34, 'The Final Frontier', '1973-11-05', '4:55', 'Rock'),
                (35, 'Fading Light', '1974-02-28', '4:15', 'Ballad'),
                (35, 'The Flame', '1974-02-28', '3:30', 'Piano Rock'),
                (35, 'Marilyn´s Ghost', '1974-02-28', '5:05', 'Pop Rock'),
                (35, 'The Last Dance', '1974-02-28', '3:28', 'Soft Rock'),
                (35, 'Epitaph', '1974-02-28', '4:42', 'Power Ballad'),
                (36, 'Ballet Shoes', '1975-05-12', '4:20', 'Soft Rock'),
                (36, 'Little Ballerina', '1975-05-12', '3:50', 'Pop Rock'),
                (36, 'The Studio Floor', '1975-05-12', '5:00', 'Piano Rock'),
                (36, 'Pirouette', '1975-05-12', '3:12', 'Ballad'),
                (36, 'Spotlight', '1975-05-12', '4:35', 'Country Rock'),
                (37, 'The Hero´s Theme', '1976-08-25', '4:35', 'Rock'),
                (37, 'Sidekick', '1976-08-25', '3:45', 'Pop Rock'),
                (37, 'Comic Book Pages', '1976-08-25', '5:10', 'Progressive Rock'),
                (37, 'The Origin Story', '1976-08-25', '3:22', 'Ballad'),
                (37, 'Superstar', '1976-08-25', '4:59', 'Arena Rock'),
                (38, 'The Color of Sadness', '1977-10-30', '4:08', 'Soft Rock'),
                (38, 'Tears on the Piano', '1977-10-30', '3:58', 'Ballad'),
                (38, 'Midnight Blue', '1977-10-30', '5:05', 'Pop'),
                (38, 'Slow Dance', '1977-10-30', '3:15', 'Jazz Fusion'),
                (38, 'Deep Water', '1977-10-30', '4:40', 'Piano Rock'),
                (39, 'No Limit', '1978-01-15', '4:10', 'Pop Rock'),
                (39, 'Bottom of the Barrel', '1978-01-15', '3:30', 'New Wave'),
                (39, 'Climbing Up', '1978-01-15', '5:00', 'Synth Pop'),
                (39, 'The Temperature Drop', '1978-01-15', '3:25', 'Rock'),
                (39, 'Under Pressure', '1978-01-15', '4:52', 'Arena Rock'),
                (40, 'Heartbreak Anthem', '1979-03-20', '4:00', 'Disco'),
                (40, 'The Cure', '1979-03-20', '3:40', 'Pop'),
                (40, 'Lost in the Groove', '1979-03-20', '5:15', 'Dance-Pop'),
                (40, 'Achy Breaky Heart', '1979-03-20', '3:25', 'Ballad'),
                (40, 'The Price of Romance', '1979-03-20', '4:30', 'Soft Rock'),
                (41, 'Touch of Genius', '1965-05-01', '3:05', 'Soul'),
                (41, 'A Little Rhythm', '1965-05-01', '2:50', 'R&B'),
                (41, 'The Organ Solo', '1965-05-01', '4:15', 'Motown'),
                (41, 'Braille Music', '1965-05-01', '3:25', 'Jazz'),
                (41, 'Baby Grand', '1965-05-01', '3:18', 'Pop'),
                (42, 'Feeling Good', '1966-07-15', '3:30', 'Soul'),
                (42, 'No Worries', '1966-07-15', '2:55', 'R&B'),
                (42, 'The Groove', '1966-07-15', '4:00', 'Motown'),
                (42, 'High Energy', '1966-07-15', '3:12', 'Pop'),
                (42, 'Soul Train Starter', '1966-07-15', '3:45', 'Funk'),
                (43, 'A Letter to You', '1967-09-20', '3:45', 'Soul'),
                (43, 'Delivered with Love', '1967-09-20', '3:15', 'R&B'),
                (43, 'Stamped and Sent', '1967-09-20', '4:20', 'Motown'),
                (43, 'The Postman´s Whistle', '1967-09-20', '2:59', 'Pop'),
                (43, 'Special Delivery', '1967-09-20', '4:05', 'Ballad'),
                (44, 'Chromatic Scale', '1968-11-25', '4:10', 'Jazz Fusion'),
                (44, 'The Right Note', '1968-11-25', '3:50', 'Soul'),
                (44, 'Minor Chord', '1968-11-25', '5:00', 'R&B'),
                (44, 'Harmonious Life', '1968-11-25', '3:25', 'Pop'),
                (44, 'Piano Interlude', '1968-11-25', '4:48', 'Funk'),
                (45, 'Mind´s Eye', '1969-02-10', '4:30', 'Funk'),
                (45, 'Cosmic View', '1969-02-10', '3:55', 'Soul'),
                (45, 'Seeing is Believing', '1969-02-10', '5:10', 'R&B'),
                (45, 'The Sixth Sense', '1969-02-10', '3:18', 'Jazz Fusion'),
                (45, 'Mental Picture', '1969-02-10', '4:52', 'Pop'),
                (46, 'The Storyteller', '1970-05-20', '4:08', 'Soul'),
                (46, 'Written in Sound', '1970-05-20', '3:35', 'R&B'),
                (46, 'Unspoken Words', '1970-05-20', '5:20', 'Funk'),
                (46, 'Page Turner', '1970-05-20', '3:10', 'Pop'),
                (46, 'A Blind Man´s Tale', '1970-05-20', '4:45', 'Ballad'),
                (47, 'Summer Heat', '1971-08-30', '4:25', 'Funk'),
                (47, 'Desert Sun', '1971-08-30', '3:50', 'Reggae'),
                (47, 'Tropical Storm', '1971-08-30', '5:05', 'Soul'),
                (47, 'Under the Palms', '1971-08-30', '3:20', 'Pop'),
                (47, 'Fire Dance', '1971-08-30', '4:38', 'R&B'),
                (48, 'Satisfaction Guaranteed', '1972-10-12', '4:15', 'Soul'),
                (48, 'Complete Harmony', '1972-10-12', '3:40', 'R&B'),
                (48, 'The Circle is Whole', '1972-10-12', '5:00', 'Jazz Fusion'),
                (48, 'Total Bliss', '1972-10-12', '3:05', 'Funk'),
                (48, 'Finished Product', '1972-10-12', '4:59', 'Pop'),
                (49, 'The Long Road', '1973-01-05', '4:40', 'Progressive Soul'),
                (49, 'Endless Miles', '1973-01-05', '3:30', 'Funk'),
                (49, 'Destination Unknown', '1973-01-05', '5:15', 'R&B'),
                (49, 'Traveling Man', '1973-01-05', '3:22', 'Pop'),
                (49, 'Passport to Sound', '1973-01-05', '4:05', 'Jazz'),
                (50, 'Inner Melody', '1974-03-18', '4:20', 'Soul'),
                (50, 'The Sound Machine', '1974-03-18', '3:55', 'Synth Funk'),
                (50, 'Creative Flow', '1974-03-18', '5:10', 'R&B'),
                (50, 'The Headphone Mix', '1974-03-18', '3:15', 'Pop'),
                (50, 'Visions of Sound', '1974-03-18', '4:48', 'Jazz Fusion'),
                (51, 'Holiday Night', '1983-07-27', '3:45', 'Dance-Pop'),
                (51, 'The First Beat', '1983-07-27', '3:50', 'New Wave'),
                (51, 'Club Anthem 83', '1983-07-27', '4:10', 'Pop'),
                (51, 'Early Confession', '1983-07-27', '3:25', 'Synth Pop'),
                (51, 'Boy Toy', '1983-07-27', '4:30', 'Disco'),
                (52, 'Material Girl II', '1984-11-12', '4:00', 'Dance-Pop'),
                (52, 'The Wedding Veil', '1984-11-12', '3:55', 'Pop'),
                (52, 'Diamond Drip', '1984-11-12', '5:00', 'New Wave'),
                (52, '80s Breakdown', '1984-11-12', '3:30', 'Synth Pop'),
                (52, 'Express Yourself (Early Mix)', '1984-11-12', '4:45', 'Hi-NRG'),
                (53, 'Island Beat', '1986-06-30', '4:15', 'Pop'),
                (53, 'Papa Don´t Preach (The Sequel)', '1986-06-30', '3:48', 'Dance-Pop'),
                (53, 'Spanish Eyes', '1986-06-30', '5:10', 'Latin Pop'),
                (53, 'Open Your Heartbeat', '1986-06-30', '3:22', 'Synth Pop'),
                (53, 'La Isla Bonita II', '1986-06-30', '4:50', 'Ballad'),
                (54, 'The Altar', '1989-03-21', '4:30', 'Pop Rock'),
                (54, 'Vogue (Early Cut)', '1989-03-21', '3:55', 'Dance-Pop'),
                (54, 'Secret Church', '1989-03-21', '5:05', 'Ballad'),
                (54, 'Gospel Groove', '1989-03-21', '3:18', 'House'),
                (54, 'Religious Experience', '1989-03-21', '4:40', 'Synth Pop'),
                (55, 'Sex Shop', '1992-10-20', '4:05', 'Deep House'),
                (55, 'Justify My Love II', '1992-10-20', '3:40', 'Trip Hop'),
                (55, 'Naked Ambition', '1992-10-20', '5:15', 'Pop'),
                (55, 'The Book', '1992-10-20', '3:25', 'R&B'),
                (55, 'Sleek and Chic', '1992-10-20', '4:59', 'Dance-Pop'),
                (56, 'Lullaby for a Lover', '1994-03-28', '4:10', 'R&B'),
                (56, 'The Secret Garden', '1994-03-28', '3:50', 'Pop'),
                (56, 'Hush Now', '1994-03-28', '5:00', 'Trip Hop'),
                (56, 'Midnight Confession', '1994-03-28', '3:12', 'Ballad'),
                (56, 'Dream State', '1994-03-28', '4:35', 'Adult Contemporary'),
                (57, 'The Vortex', '1998-02-22', '4:25', 'Electronica'),
                (57, 'Sky Full of Stars', '1998-02-22', '3:55', 'Trance'),
                (57, 'The Rebirth', '1998-02-22', '5:20', 'Dance-Pop'),
                (57, 'Power of Goodbye (II)', '1998-02-22', '3:40', 'Ambient Pop'),
                (57, 'Metamorphosis', '1998-02-22', '4:52', 'Pop'),
                (58, 'French Touch', '2000-09-18', '4:00', 'Dance-Pop'),
                (58, 'Turn Up The Volume', '2000-09-18', '3:30', 'House'),
                (58, 'The Mixer', '2000-09-18', '5:15', 'Electronica'),
                (58, 'Groovy Thing', '2000-09-18', '3:28', 'Pop'),
                (58, 'Country Club Remix', '2000-09-18', '4:40', 'Folk Pop'),
                (59, 'Hollywood Dreams', '2003-04-08', '4:10', 'Folk Pop'),
                (59, 'The American Nightmare', '2003-04-08', '3:45', 'Acoustic'),
                (59, 'Patriotism', '2003-04-08', '5:05', 'Electronica'),
                (59, 'The Flag', '2003-04-08', '3:15', 'Pop'),
                (59, 'Consumerism', '2003-04-08', '4:35', 'Dance-Pop'),
                (60, 'Dance Floor Sins', '2005-05-14', '4:20', 'Disco'),
                (60, 'Future Disco', '2005-05-14', '3:50', 'House'),
                (60, 'Mirrorball Effect', '2005-05-14', '5:00', 'Dance-Pop'),
                (60, 'The DJ´s Set', '2005-05-14', '3:25', 'Electronic'),
                (60, 'Forgiveness', '2005-05-14', '4:55', 'Pop'),
                (61, 'Funk Overture', '1978-04-07', '4:05', 'Funk'),
                (61, 'Piano Man´s Groove', '1978-04-07', '3:30', 'R&B'),
                (61, 'A Solo Effort', '1978-04-07', '5:15', 'Soul'),
                (61, 'The Purple Prelude', '1978-04-07', '3:20', 'Pop'),
                (61, 'Future Shock Funk', '1978-04-07', '4:40', 'Jazz Funk'),
                (62, 'I Wanna Be Your Lover II', '1979-10-19', '3:45', 'Funk Pop'),
                (62, 'Sexuality Dance', '1979-10-19', '3:55', 'R&B'),
                (62, 'Electric Kiss', '1979-10-19', '5:00', 'New Wave'),
                (62, 'Dirty Mind (Early Draft)', '1979-10-19', '3:35', 'Funk Rock'),
                (62, 'When Doves Cry (Acoustic)', '1979-10-19', '4:48', 'Ballad'),
                (63, 'Filthy Thoughts', '1980-10-08', '3:00', 'New Wave'),
                (63, 'Headphones', '1980-10-08', '2:50', 'Funk'),
                (63, 'Nasty Groove', '1980-10-08', '4:10', 'Pop'),
                (63, 'The B Sides of Me', '1980-10-08', '3:25', 'Synth Pop'),
                (63, 'Bedroom Pop', '1980-10-08', '4:30', 'Punk Funk'),
                (64, 'Uproar', '1981-10-14', '4:12', 'Funk Rock'),
                (64, 'The Debate', '1981-10-14', '3:55', 'New Wave'),
                (64, 'Political Funk', '1981-10-14', '5:00', 'Pop'),
                (64, 'Riot Gear', '1981-10-14', '3:28', 'Synth Pop'),
                (64, 'Talk of the Town', '1981-10-14', '4:35', 'R&B'),
                (65, 'Party Like It´s...', '1982-10-27', '4:20', 'Dance-Pop'),
                (65, 'The End of the World', '1982-10-27', '3:50', 'Synth Funk'),
                (65, 'Future Shock 82', '1982-10-27', '5:10', 'New Wave'),
                (65, 'Millennium Groove', '1982-10-27', '3:12', 'Pop'),
                (65, 'A New Era', '1982-10-27', '4:59', 'Funk Rock'),
                (66, 'The Artist´s Lament', '1984-06-25', '4:30', 'Power Ballad'),
                (66, 'Electric Heartbreak', '1984-06-25', '3:55', 'Rock'),
                (66, 'Lake Minnetonka', '1984-06-25', '5:05', 'Pop Rock'),
                (66, 'The Movie Score', '1984-06-25', '3:18', 'New Wave'),
                (66, 'Doves of Peace', '1984-06-25', '4:40', 'Arena Rock'),
                (67, 'Global Funk', '1985-11-15', '4:05', 'Pop'),
                (67, 'Foreign Affairs', '1985-11-15', '3:40', 'Dance-Pop'),
                (67, 'Passport to Groove', '1985-11-15', '5:15', 'New Wave'),
                (67, 'The World Tour', '1985-11-15', '3:25', 'Funk'),
                (67, 'International Lover', '1985-11-15', '4:50', 'R&B'),
                (68, 'The Grand March', '1986-03-31', '4:10', 'Pop'),
                (68, 'Brass Band Funk', '1986-03-31', '3:50', 'Funk'),
                (68, 'Under the Big Top', '1986-03-31', '5:00', 'New Wave'),
                (68, 'Confetti Rain', '1986-03-31', '3:12', 'Dance-Pop'),
                (68, 'Carnival', '1986-03-31', '4:35', 'Jazz Fusion'),
                (69, 'The Prophecy', '1987-03-30', '4:20', 'Funk Rock'),
                (69, 'Modern Anxiety', '1987-03-30', '3:55', 'Pop'),
                (69, 'Dark Times', '1987-03-30', '5:05', 'R&B'),
                (69, 'The Symbol', '1987-03-30', '3:20', 'New Wave'),
                (69, 'The Time Capsule', '1987-03-30', '4:52', 'Acoustic'),
                (70, 'Joy and Ecstasy', '1988-05-10', '4:30', 'Funk'),
                (70, 'The Garden of Eden', '1988-05-10', '3:40', 'R&B'),
                (70, 'New Power Generation (Theme)', '1988-05-10', '5:10', 'Pop'),
                (70, 'The Love Song', '1988-05-10', '3:25', 'Ballad'),
                (70, 'Sexual Healing (The Remix)', '1988-05-10', '4:55', 'Dance-Pop'),
                (71, 'The Message', '1973-01-05', '4:10', 'Folk Rock'),
                (71, 'Street Light Serenade', '1973-01-05', '3:30', 'Rock'),
                (71, 'New Jersey Shore', '1973-01-05', '5:00', 'Blues Rock'),
                (71, 'The Introduction', '1973-01-05', '3:15', 'Folk'),
                (71, 'A Story Begins', '1973-01-05', '4:25', 'Acoustic'),
                (72, 'The Great Escape', '1975-05-15', '4:25', 'Rock'),
                (72, 'Born to Run (Acoustic)', '1975-05-15', '3:45', 'Folk Rock'),
                (72, 'Thunder Road (Early Draft)', '1975-05-15', '5:10', 'Heartland Rock'),
                (72, 'Jungle Beat', '1975-05-15', '3:20', 'Blues Rock'),
                (72, 'The River (Rough Cut)', '1975-05-15', '4:55', 'Ballad'),
                (73, 'The Race', '1975-08-25', '4:30', 'Rock'),
                (73, 'Summer´s End', '1975-08-25', '3:55', 'Heartland Rock'),
                (73, 'Across the Line', '1975-08-25', '5:05', 'Pop Rock'),
                (73, 'The Promise', '1975-08-25', '3:28', 'Ballad'),
                (73, 'On the Turnpike', '1975-08-25', '4:40', 'Arena Rock'),
                (74, 'Shadows Fall', '1978-06-02', '4:15', 'Rock'),
                (74, 'Badlands II', '1978-06-02', '3:40', 'Heartland Rock'),
                (74, 'Night Patrol', '1978-06-02', '5:00', 'Blues Rock'),
                (74, 'The Weight', '1978-06-02', '3:25', 'Ballad'),
                (74, 'Factory Floor', '1978-06-02', '4:59', 'Working-Class Rock'),
                (75, 'Two Hearts (The Duet)', '1980-10-17', '4:20', 'Pop Rock'),
                (75, 'The Wedding Day', '1980-10-17', '3:50', 'Heartland Rock'),
                (75, 'Hungry Heart II', '1980-10-17', '5:00', 'Rock'),
                (75, 'The Promise (Live)', '1980-10-17', '3:12', 'Ballad'),
                (75, 'Family Ties', '1980-10-17', '4:35', 'Folk Rock'),
                (76, 'The Highway Patrolman (Solo)', '1982-09-30', '4:40', 'Acoustic'),
                (76, 'Empty Fields', '1982-09-30', '3:30', 'Folk'),
                (76, 'The Badlands', '1982-09-30', '5:15', 'Country Folk'),
                (76, 'A Lonely Drive', '1982-09-30', '3:22', 'Ballad'),
                (76, 'The Pistol', '1982-09-30', '4:05', 'Americana'),
                (77, 'Working Man´s Anthem', '1984-06-04', '4:30', 'Heartland Rock'),
                (77, 'Glory Days (The Long Cut)', '1984-06-04', '3:55', 'Pop Rock'),
                (77, 'The Factory Town', '1984-06-04', '5:05', 'Rock'),
                (77, 'Cover Me (Acoustic)', '1984-06-04', '3:18', 'Ballad'),
                (77, 'The Vietnam Vet', '1984-06-04', '4:40', 'Arena Rock'),
                (78, 'The Proposal', '1987-10-09', '4:15', 'Soft Rock'),
                (78, 'Two Ships', '1987-10-09', '3:40', 'Pop Rock'),
                (78, 'A Thousand Kisses', '1987-10-09', '5:00', 'Ballad'),
                (78, 'The Marriage', '1987-10-09', '3:25', 'Synth Pop'),
                (78, 'Love´s Hard Road', '1987-10-09', '4:52', 'Rock'),
                (79, 'The Warmth', '1992-03-31', '4:20', 'Pop Rock'),
                (79, 'Emotional Connection', '1992-03-31', '3:50', 'Soft Rock'),
                (79, 'A Gentle Hand', '1992-03-31', '5:10', 'Ballad'),
                (79, 'Kindness of Strangers', '1992-03-31', '3:33', 'Rock'),
                (79, 'The Comfort', '1992-03-31', '4:02', 'Heartland Rock'),
                (80, 'Jackpot', '1992-03-31', '4:30', 'Rock'),
                (80, 'The Best Bet', '1992-03-31', '3:55', 'Pop Rock'),
                (80, 'Roll of the Dice', '1992-03-31', '5:05', 'Heartland Rock'),
                (80, 'Winning Streak', '1992-03-31', '3:20', 'Blues Rock'),
                (80, 'The Good Life', '1992-03-31', '4:40', 'Folk Rock'),
                (81, 'The First Step', '1993-07-05', '3:50', 'Art Pop'),
                (81, 'Human Behavior II', '1993-07-05', '4:15', 'Electronic'),
                (81, 'Post (Early Sketch)', '1993-07-05', '5:00', 'Trip Hop'),
                (81, 'Icelandic Dawn', '1993-07-05', '3:25', 'Experimental'),
                (81, 'Childhood Vision', '1993-07-05', '4:35', 'Jazz Fusion'),
                (82, 'Sending a Message', '1995-06-13', '4:20', 'Electronic'),
                (82, 'Hyperballad II', '1995-06-13', '3:55', 'Trip Hop'),
                (82, 'The Envelope', '1995-06-13', '5:10', 'Art Pop'),
                (82, 'Emotional Mail', '1995-06-13', '3:12', 'Dance-Pop'),
                (82, 'Telegram', '1995-06-13', '4:59', 'Experimental'),
                (83, 'Volcano', '1997-09-22', '4:30', 'Art Pop'),
                (83, 'Bells and Strings', '1997-09-22', '3:40', 'Trip Hop'),
                (83, 'Isobel II', '1997-09-22', '5:00', 'Electronic'),
                (83, 'The Single Cell', '1997-09-22', '3:25', 'Experimental'),
                (83, 'Mountain Peak', '1997-09-22', '4:55', 'Abstract'),
                (84, 'Winter Serenade', '2001-08-27', '4:05', 'Ambient'),
                (84, 'Hidden Places', '2001-08-27', '3:30', 'Microbeat'),
                (84, 'Cozy Home', '2001-08-27', '5:15', 'Experimental'),
                (84, 'The Swan Song', '2001-08-27', '3:22', 'Art Pop'),
                (84, 'Cocoon II', '2001-08-27', '4:40', 'Downtempo'),
                (85, 'Vocal Chord', '2004-08-30', '4:10', 'A Cappella'),
                (85, 'The Bone Marrow', '2004-08-30', '3:50', 'Experimental'),
                (85, 'Mouth Music', '2004-08-30', '5:00', 'Art Pop'),
                (85, 'Human Sound', '2004-08-30', '3:12', 'Electronic'),
                (85, 'Breathing', '2004-08-30', '4:35', 'Avant-Garde'),
                (86, 'Earthquake', '2007-05-01', '4:25', 'Electronic'),
                (86, 'Brass Band Fury', '2007-05-01', '3:55', 'Art Pop'),
                (86, 'Global Beat', '2007-05-01', '5:05', 'World Music'),
                (86, 'Pulsating Heart', '2007-05-01', '3:18', 'Experimental'),
                (86, 'Innocence II', '2007-05-01', '4:52', 'Dance-Pop'),
                (87, 'Cosmic Web', '2011-10-05', '4:30', 'Electronic'),
                (87, 'DNA Spiral', '2011-10-05', '3:40', 'Ambient'),
                (87, 'The Moon Song', '2011-10-05', '5:10', 'Art Pop'),
                (87, 'Virus of Sound', '2011-10-05', '3:25', 'Experimental'),
                (87, 'Crystalline (The Sequel)', '2011-10-05', '4:55', 'Microbeat'),
                (88, 'Mending a Heart', '2015-01-20', '4:05', 'String Pop'),
                (88, 'The Breakup', '2015-01-20', '3:30', 'Electronic'),
                (88, 'Violin Tears', '2015-01-20', '5:15', 'Art Pop'),
                (88, 'Healing Process', '2015-01-20', '3:22', 'Ambient'),
                (88, 'Loss', '2015-01-20', '4:40', 'Experimental'),
                (89, 'The Perfect World', '2017-11-24', '4:10', 'Electronic'),
                (89, 'Flute Song', '2017-11-24', '3:50', 'Ambient'),
                (89, 'New Colony', '2017-11-24', '5:00', 'Art Pop'),
                (89, 'The Garden', '2017-11-24', '3:12', 'Experimental'),
                (89, 'Future is Female', '2017-11-24', '4:35', 'Dance-Pop'),
                (90, 'Under the Surface', '2022-09-30', '4:20', 'Electronic'),
                (90, 'Mushroom Underground', '2022-09-30', '3:55', 'Art Pop'),
                (90, 'The Mycelium Network', '2022-09-30', '5:05', 'Gabber'),
                (90, 'Deep Roots', '2022-09-30', '3:28', 'Ambient'),
                (90, 'The Fungal Bloom', '2022-09-30', '4:40', 'Experimental'),
                (91, 'The Piano Man´s Daughter', '1958-02-01', '4:00', 'Jazz'),
                (91, 'Feeling Good (Early Draft)', '1958-02-01', '3:30', 'Blues'),
                (91, 'My Baby Just Cares for Me II', '1958-02-01', '5:15', 'Vocal Jazz'),
                (91, 'New York City Blues', '1958-02-01', '3:20', 'R&B'),
                (91, 'A Solo Night', '1958-02-01', '4:25', 'Ballad'),
                (92, 'Simply Wonderful', '1959-06-10', '4:10', 'Vocal Jazz'),
                (92, 'Black and Blue', '1959-06-10', '3:50', 'Blues'),
                (92, 'The High Priestess', '1959-06-10', '5:00', 'Jazz'),
                (92, 'A Star is Born', '1959-06-10', '3:05', 'R&B'),
                (92, 'Unstoppable', '1959-06-10', '4:35', 'Soul'),
                (93, 'Live in New York', '1962-05-03', '4:30', 'Vocal Jazz'),
                (93, 'A Night Out', '1962-05-03', '3:55', 'Blues'),
                (93, 'The Gatekeeper', '1962-05-03', '5:20', 'Gospel'),
                (93, 'Performance Power', '1962-05-03', '3:15', 'Soul'),
                (93, 'The Applause', '1962-05-03', '4:48', 'Jazz'),
                (94, 'The Temptation', '1961-06-01', '4:05', 'Vocal Jazz'),
                (94, 'Eve´s Song', '1961-06-01', '3:40', 'Blues'),
                (94, 'Hidden Desires', '1961-06-01', '5:10', 'Soul'),
                (94, 'The Serpent´s Whisper', '1961-06-01', '3:28', 'Pop'),
                (94, 'Sweet Danger', '1961-06-01', '4:40', 'Ballad'),
                (95, 'Untamed', '1966-12-29', '4:15', 'Vocal Jazz'),
                (95, 'Stormy Weather', '1966-12-29', '3:30', 'Blues'),
                (95, 'The Hurricane', '1966-12-29', '5:05', 'Soul'),
                (95, 'A Strong Wind Blows', '1966-12-29', '3:22', 'Pop'),
                (95, 'Free Spirit', '1966-12-29', '4:52', 'Ballad'),
                (96, 'Smooth Operator', '1967-09-04', '4:20', 'Soul'),
                (96, 'Velvet Voice', '1967-09-04', '3:55', 'Vocal Jazz'),
                (96, 'Soft and Strong', '1967-09-04', '5:00', 'Blues'),
                (96, 'A Gentle Touch', '1967-09-04', '3:15', 'Pop'),
                (96, 'Luxury', '1967-09-04', '4:35', 'R&B'),
                (97, 'Enough is Enough', '1968-05-20', '4:10', 'Soul'),
                (97, 'The Statement', '1968-05-20', '3:40', 'Blues'),
                (97, 'Silence Speaks', '1968-05-20', '5:10', 'Jazz'),
                (97, 'The Protest', '1968-05-20', '3:25', 'Gospel'),
                (97, 'No More Words', '1968-05-20', '4:59', 'Vocal Jazz'),
                (98, 'Love is a Verb', '1969-06-12', '4:00', 'Soul'),
                (98, 'The Bee Gees Cover', '1969-06-12', '3:25', 'Pop'),
                (98, 'A Deep Feeling', '1969-06-12', '5:15', 'Blues'),
                (98, 'The Human Connection', '1969-06-12', '3:33', 'Vocal Jazz'),
                (98, 'Devotion', '1969-06-12', '4:02', 'Ballad'),
                (99, 'The African Sun', '1970-09-30', '4:30', 'Soul'),
                (99, 'Precious Metal', '1970-09-30', '3:55', 'Blues'),
                (99, 'The Continent', '1970-09-30', '5:05', 'Gospel'),
                (99, 'Black is Beautiful', '1970-09-30', '3:20', 'Jazz'),
                (99, 'The Richness', '1970-09-30', '4:40', 'Vocal Jazz'),
                (100, 'The Critical Hour', '1972-05-15', '4:20', 'Soul'),
                (100, 'The Doctor', '1972-05-15', '3:50', 'Blues'),
                (100, 'Call 911', '1972-05-15', '5:00', 'Jazz'),
                (100, 'Healing Music', '1972-05-15', '3:25', 'Gospel'),
                (100, 'A Moment of Crisis', '1972-05-15', '4:55', 'Vocal Jazz');
            """.trimIndent()
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS songs")
        db.execSQL("DROP TABLE IF EXISTS albums")
        db.execSQL("DROP TABLE IF EXISTS artists")
        onCreate(db)

    }
    fun getAlbumById(id: Int): Album? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM albums WHERE id = ?", arrayOf(id.toString()))

        return if (cursor.moveToFirst()) {
            val album = Album(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                artist = cursor.getString(cursor.getColumnIndexOrThrow("artist")),
                genre = cursor.getString(cursor.getColumnIndexOrThrow("genre")),
                releaseDate = cursor.getString(cursor.getColumnIndexOrThrow("releaseDate"))
            )
            cursor.close()
            album
        } else {
            cursor.close()
            null
        }
    }

    fun getAll(): List<Artist> {

        val list = mutableListOf<Artist>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM artists",null)

        while(cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val stature = cursor.getDouble(cursor.getColumnIndexOrThrow("stature"))
            val nationality = cursor.getString(cursor.getColumnIndexOrThrow("nationality"))
            val birthdate = cursor.getString(cursor.getColumnIndexOrThrow("birthdate"))

            list.add(Artist(id,name,stature,nationality,birthdate))

        }

        cursor.close()
        return list

    }
    fun getArtistById(id: Int): Artist? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM artists WHERE id = ?", arrayOf(id.toString()))

        return if (cursor.moveToFirst()) {
            val artist = Artist(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                stature = cursor.getDouble(cursor.getColumnIndexOrThrow("stature")),
                nationality = cursor.getString(cursor.getColumnIndexOrThrow("nationality")),
                birthdate = cursor.getString(cursor.getColumnIndexOrThrow("birthdate"))
            )
            cursor.close()
            artist
        } else {
            cursor.close()
            null
        }
    }

    fun getSongById(id: Int): Song? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM songs WHERE id = ?", arrayOf(id.toString()))

        return if (cursor.moveToFirst()) {
            val song = Song(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                artist = cursor.getString(cursor.getColumnIndexOrThrow("artist")),
                album = cursor.getString(cursor.getColumnIndexOrThrow("album")),
                genre = cursor.getString(cursor.getColumnIndexOrThrow("genre")),
                duration = cursor.getString(cursor.getColumnIndexOrThrow("duration")),
                releaseDate = cursor.getString(cursor.getColumnIndexOrThrow("releaseDate"))
            )
            cursor.close()
            song
        } else {
            cursor.close()
            null
        }
    }

    fun getOne(name: String): List<Artist> {

        val list = mutableListOf<Artist>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM artists WHERE name LIKE ?",arrayOf("${name}%"))

        while(cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val stature = cursor.getDouble(cursor.getColumnIndexOrThrow("stature"))
            val nationality = cursor.getString(cursor.getColumnIndexOrThrow("nationality"))
            val birthdate = cursor.getString(cursor.getColumnIndexOrThrow("birthdate"))

            list.add(Artist(id,name,stature,nationality,birthdate))

        }

        cursor.close()
        return list

    }

}