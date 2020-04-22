package io.bloco.biblioteca.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.bloco.biblioteca.helpers.Converters;
import io.bloco.biblioteca.model.Book;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Book> __insertionAdapterOfBook;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<Book> __insertionAdapterOfBook_1;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBookById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBookByTitle;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllBooks;

  public BookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBook = new EntityInsertionAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Book` (`id`,`title`,`author`,`publishDate`,`isbn`,`read`,`uriCover`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        final Long _tmp;
        _tmp = __converters.fromDate(value.getPublishDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        if (value.getIsbn() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIsbn());
        }
        final int _tmp_1;
        _tmp_1 = value.getRead() ? 1 : 0;
        stmt.bindLong(6, _tmp_1);
        if (value.getUriCover() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUriCover());
        }
      }
    };
    this.__insertionAdapterOfBook_1 = new EntityInsertionAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Book` (`id`,`title`,`author`,`publishDate`,`isbn`,`read`,`uriCover`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        final Long _tmp;
        _tmp = __converters.fromDate(value.getPublishDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        if (value.getIsbn() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIsbn());
        }
        final int _tmp_1;
        _tmp_1 = value.getRead() ? 1 : 0;
        stmt.bindLong(6, _tmp_1);
        if (value.getUriCover() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUriCover());
        }
      }
    };
    this.__preparedStmtOfDeleteBookById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Book WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteBookByTitle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Book WHERE title = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllBooks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Book";
        return _query;
      }
    };
  }

  @Override
  public void insertBook(final Book newBook) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBook.insert(newBook);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMultipleBooks(final List<Book> books) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBook_1.insert(books);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBookById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBookById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteBookById.release(_stmt);
    }
  }

  @Override
  public void deleteBookByTitle(final String title) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBookByTitle.acquire();
    int _argIndex = 1;
    if (title == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, title);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteBookByTitle.release(_stmt);
    }
  }

  @Override
  public void deleteAllBooks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllBooks.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllBooks.release(_stmt);
    }
  }

  @Override
  public Book getBookById(final int id) {
    final String _sql = "SELECT * FROM Book WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfPublishDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishDate");
      final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfUriCover = CursorUtil.getColumnIndexOrThrow(_cursor, "uriCover");
      final Book _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        final Date _tmpPublishDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPublishDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPublishDate);
        }
        _tmpPublishDate = __converters.toDate(_tmp);
        final String _tmpIsbn;
        _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final String _tmpUriCover;
        _tmpUriCover = _cursor.getString(_cursorIndexOfUriCover);
        _result = new Book(_tmpId,_tmpTitle,_tmpAuthor,_tmpPublishDate,_tmpIsbn,_tmpRead,_tmpUriCover);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Book> getAllBooks() {
    final String _sql = "SELECT * FROM Book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfPublishDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishDate");
      final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfUriCover = CursorUtil.getColumnIndexOrThrow(_cursor, "uriCover");
      final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Book _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        final Date _tmpPublishDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPublishDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPublishDate);
        }
        _tmpPublishDate = __converters.toDate(_tmp);
        final String _tmpIsbn;
        _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
        final boolean _tmpRead;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRead);
        _tmpRead = _tmp_1 != 0;
        final String _tmpUriCover;
        _tmpUriCover = _cursor.getString(_cursorIndexOfUriCover);
        _item = new Book(_tmpId,_tmpTitle,_tmpAuthor,_tmpPublishDate,_tmpIsbn,_tmpRead,_tmpUriCover);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countBooks() {
    final String _sql = "SELECT count(*) FROM Book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
