import CityList from "./containers/cities/CityList";
import CityDetails from "./containers/cities/CityDetails";


export const routes = [
    {
        path: '/cities',
        Component: CityList,
        layout: 'default',
        name: 'Города',
    },
    {
        path: '/cities/:id',
        Component: CityDetails,
        layout: 'default',
        name: 'Город',
    }
]