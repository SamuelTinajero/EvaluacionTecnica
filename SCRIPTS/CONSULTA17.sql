SELECT
    a.idProducto,
    a.nombre,
    ISNULL(SUM(a.precio * b.cantidad), 0) AS total
FROM productos a
LEFT JOIN ventas b ON a.idProducto = b.idProducto
GROUP BY a.idProducto, a.nombre