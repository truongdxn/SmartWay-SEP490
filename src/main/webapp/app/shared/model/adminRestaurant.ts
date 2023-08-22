export interface IAdminRestaurant {
  id?: string;
  name?: string | null;
  phone?: string | null;
  currencyUnit?: string | null;
  planExpiry?: Date | null;
  langKey?: string | null;
  isActive?: boolean | null;
}

export const defaultValue: Readonly<IAdminRestaurant> = {};
