create or replace function encode_uri_component(text)
returns text
language plv8
strict immutable
as $$
  return encodeURIComponent($1);
$$;