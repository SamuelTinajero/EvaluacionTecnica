SELECT DISTINCT
    a.idProducto,
    a.nombre
FROM productos a
INNER JOIN ventas b ON a.idProducto = b.idProducto;