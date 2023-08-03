import restaurant from './restaurant/restaurant.reducer';
import menuItem from './menu-item/menu-item.reducer';
import menuItemCategory from './menu-item-category/menu-item-category.reducer';
import staff from './staff/staff.reducer';
import role from './role/role.reducer';
import diningTable from './dining-table/dining-table.reducer';
import zone from './zone/zone.reducer';
import order from '../selling/order/order.reducer';
import kitchen from '../selling/kitchen/kitchen.reducer';
import bankAccount from '../check-bank-account-tenant/check-bank-account-tenant.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  restaurant,
  menuItem,
  menuItemCategory,
  staff,
  diningTable,
  zone,
  role,
  order,
  kitchen,
  bankAccount,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
