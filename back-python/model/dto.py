from typing import Optional

from pydantic import BaseModel


class City(BaseModel):
    id: Optional[int]
    name: Optional[str]

    def __repr__(self):
        return f"City(id={self.id!r}, country={self.name!r}))"