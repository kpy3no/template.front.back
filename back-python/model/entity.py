from sqlalchemy import Column, Integer, String

from sqlalchemy.orm import declarative_base

Base = declarative_base()


class CityEntity(Base):
    __tablename__ = 'CITY'
    id = Column(Integer, primary_key=True)
    name = Column(String, nullable=False)

    def __repr__(self):
        return f"City(id={self.id!r}, name={self.name!r}))"