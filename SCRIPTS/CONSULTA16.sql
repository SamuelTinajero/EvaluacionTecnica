SELECT
    a.idProducto,
    a.nombre,
    SUM(b.cantidad) AS total_unidades_vendidas
FROM productos a
INNER JOIN ventas b ON a.idProducto = b.idProducto
GROUP BY a.idProducto, a.nombre