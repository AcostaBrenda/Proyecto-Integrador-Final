package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT p.*FROM productos p inner join ciudades c on c.ciudades_id = p.ciudades_id \n" +
            "WHERE c.ciudades_id like :id", nativeQuery = true)
    List<Producto> findProductsByCity(@Param("id") Long id);

    @Query(value = "SELECT p.* FROM productos p inner join categorias c on c.categorias_id = p.categorias_id \n" +
            "WHERE c.categorias_id like :id", nativeQuery = true)
    List<Producto> findProductsByCategory(@Param("id") Long id);

    @Query(value = "select P.*, R.* from productos P \n" +
            "left join reservas R on P.productos_id = R.productos_id \n" +
            "where P.productos_id not in (select distinct R.productos_id from reservas R\n" +
            "where ( :fechaInicial < R.fecha_final  and  :fechaFinal > R.fecha_inicial))\n" +
            "group by P.productos_id;", nativeQuery = true)
    List<Producto> filterProductsByDates(@Param("fechaInicial") LocalDate fechaInicial, @Param("fechaFinal") LocalDate fechaFinal);

    @Query(value = "select P.*, R.* from productos P \n" +
            "left join reservas R on P.productos_id = R.productos_id \n" +
            "where (P.ciudades_id = :id) \n" +
            "and P.productos_id not in (select distinct R.productos_id from reservas R\n" +
            "where ( :fechaInicial < R.fecha_final  and  :fechaFinal > R.fecha_inicial))\n" +
            "group by P.productos_id;", nativeQuery = true)
    List<Producto> filterProductsByCityAndDates(@Param("id") Long id, @Param("fechaInicial") LocalDate fechaInicial, @Param("fechaFinal") LocalDate fechaFinal);


}
