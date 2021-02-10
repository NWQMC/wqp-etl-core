/*
    Attach a table as a range partition of a parent.  It is an error if the partition is already part of the parent, or
        if any other db partitioning rules are violated.

    Parameters are wrapped in ${} with these names:
    schema_name: Name of the schema to create the table in - required
    parent_table: Name of the table to attach the partition to - required
    table_name: Name of the table to attach - required
    range_start: The range start - required
    range_end: The range end - required

    Example complete statement:
    ALTER TABLE wqp.result_part ATTACH PARTITION wqp.result_2021_01_to_MAXVALUE FOR VALUES FROM ('2021-01-01') TO (MAXVALUE);
 */
alter table ${schema_name}.${parent_table}
    attach partition ${schema_name}.${table_name} for values from (${range_start}) to (${range_end})
