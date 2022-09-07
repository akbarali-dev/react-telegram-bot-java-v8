create or replace function increment_order_count(user_chat_id bigint, select_product_id uuid) returns integer
    language plpgsql
as
$$
declare
    user_order_count integer := (select c.quantity
                                 from card c
                                          join product p on p.id = c.product_id
                                          join users u on u.id = c.user_id
                                          join user_activity ua on u.id = ua.user_id
                                 where ua.chat_id = user_chat_id
                                   and p.id = select_product_id
                                   and c.success = false);
    product_price    float;
    select_user_id   uuid;
    select_card_id   uuid;
BEGIN
    if user_order_count IS NULL then
        product_price := (select price
                          from product
                          where id = select_product_id);
        select_user_id := (select u.id
                           from users u
                                    join user_activity a on u.id = a.user_id
                           where chat_id = user_chat_id);
        insert into card (price, quantity, product_id, user_id, added_card, success, location_id)
        values (product_price, 1, select_product_id, select_user_id, false, false, null);
        return 1;
    end if;
    select_card_id := (select c.id
                       from card c
                                join product p on p.id = c.product_id
                                join users u on u.id = c.user_id
                                join user_activity ua on u.id = ua.user_id
                       where ua.chat_id = user_chat_id
                         and p.id = select_product_id
                         and c.success = false);

    if select_card_id is null then
        return user_order_count;
    end if;

    update card c2
    set quantity = user_order_count + 1
    where c2.id = select_card_id;
    return user_order_count + 1;
end;
$$;

select increment_order_count(1474104201, '032d4adb-4727-4f75-ae5e-34baeb30130b')