import os
from pathlib import Path

from sqlalchemy import text, create_engine
from sqlalchemy.engine import Engine


def create_engine_with_new_database(db_name="database_test.db") -> Engine:
    if os.path.exists(db_name):
        os.remove(db_name)

    engine = create_engine(f"sqlite:///{db_name}?check_same_thread=False", echo=False)
    init(engine)

    return engine


def create_engine_with_existed_database(db_name="database.db") -> Engine:
    """Создает коннект к БД. Без флага ?check_same_thread=False выскакивает ошибка во время дебага:
    ProgrammingError: SQLite objects created in a thread can only be used in that same thread"""

    return create_engine(f"sqlite:///{db_name}?check_same_thread=False", echo=False)


def init(engine: Engine) -> Engine:
    with engine.connect() as con:
        # with open("../../scripts/init.sql") as file:
        with open(Path(__file__).parent.parent / 'init.sql') as file:
            queries = file.read().split(';')

            for query in queries:
                con.execute(text(query))

    return engine
