from typing import List

from sqlalchemy.orm import Session

from service.db_init import create_engine_with_new_database
from model.dto import City
from model.entity import CityEntity

engine = create_engine_with_new_database()

def get_all_city() -> List[City]:
    with Session(engine) as session:
        entities = session.query(CityEntity).all()
        dto_result = []

        for entity in entities:
            dto_result.append(from_entity_city_to_object(entity))

        return dto_result


def from_object_city_to_entity(object: City) -> CityEntity:
    return CityEntity(id=object.id, name=object.name)


def from_entity_city_to_object(entity: CityEntity) -> City:
    return City(id=entity.id, name=entity.name)
