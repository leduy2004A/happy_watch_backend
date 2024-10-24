package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.dto.GioHangChiTietDTO;
import com.example.demo.model.ChiTietGioHang;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.GioHang;
import com.example.demo.repository.ChiTietGioHangRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.GioHangRepository;
import com.example.demo.service.ChiTietHoaDonService;
import com.example.demo.service.GioHangService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart-details")
@RequiredArgsConstructor
public class ChiTietGioHangController {

    private final GioHangRepository gioHangRepository;
    private final ChiTietGioHangRepository chiTietGioHangRepository;
    private final GioHangService gioHangService;
    private final ChiTietSanPhamRepository chiTietSanPhamRepository;
    private final ChiTietHoaDonService chiTietHoaDonService;

    @GetMapping
    public ResponseEntity<Page<GioHangChiTietDTO>> getAllCart(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(gioHangService.getAllCartDetails(pageable));
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<GioHangChiTietDTO> getCartDetailById(@PathVariable Long id) {
        return gioHangService.getCartDetailById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{cartCode}")
    public ResponseEntity<List<GioHangChiTietDTO>> getCartByCartCode(@PathVariable String cartCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        GioHang gioHang = gioHangRepository.findByMa(cartCode);
        if (gioHang == null) {
            return ResponseEntity.notFound().build();
        }
        Set<ChiTietGioHang> cartDetails = gioHang.getCartDetails();

        if (cartDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetails.stream()
                .map(cartDetail -> gioHangService.getCartDetailById(cartDetail.getId()).get())
                .toList());
    }

    @GetMapping("/billDetails/{cartCode}")
    public ResponseEntity<Object> getBillDetails(@PathVariable String cartCode) {
        Set<Long> productIds = gioHangRepository.findByMa(cartCode).getCartDetails().stream()
                .map(c -> c.getChiTietSanPham().getId()).collect(Collectors.toSet());
        if (productIds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart details not found or cart is empty");
        }
        List<ChiTietHoaDonDTO> billDetails = chiTietHoaDonService.getAllChiTietHoaDonWithDetails().stream()
                .filter(billDetail -> productIds.contains(billDetail.getChiTietSanPhamId()))
                .collect(Collectors.toList());

        if (billDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bill details not exist");
        }
        return ResponseEntity.ok(billDetails);
    }

    @PostMapping
    public ResponseEntity<Object> createNewCart(@RequestBody GioHangChiTietDTO cartDetailsDTO) {
        Optional<GioHang> optionalGioHang = gioHangRepository.findById(cartDetailsDTO.getIdGioHang());
        Optional<ChiTietSanPham> optionalChiTietSanPham = chiTietSanPhamRepository
                .findById(cartDetailsDTO.getIdSanPhamChiTiet());

        if (!optionalGioHang.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("GioHang not found");
        }
        if (!optionalChiTietSanPham.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ChiTietSanPham not found");
        }

        GioHang gioHang = optionalGioHang.get();
        ChiTietSanPham chiTietSanPham = optionalChiTietSanPham.get();
        int soLuong = cartDetailsDTO.getSoLuong() < 1 ? 1 : cartDetailsDTO.getSoLuong();
        BigDecimal giaTungSanPham = cartDetailsDTO.getGiaTungSanPham();
        // Create and add a new ChiTietGioHang entity
        ChiTietGioHang cartDetail = gioHang.getCartDetails().stream()
                .filter(detail -> detail.getChiTietSanPham().getId().equals(cartDetailsDTO.getIdSanPhamChiTiet())
                        && detail.getGioHang().getId().equals(cartDetailsDTO.getIdGioHang()))
                .findFirst()
                .orElse(new ChiTietGioHang());

        if (!gioHang.getCartDetails().contains(cartDetail)) {
            cartDetail.setGioHang(gioHang);
            cartDetail.setChiTietSanPham(chiTietSanPham);
        }
        cartDetail.setSoLuong(cartDetail.getSoLuong() == null ? soLuong : cartDetail.getSoLuong() + soLuong);
        cartDetail.setGiaTungSanPham(giaTungSanPham);

        gioHang.getCartDetails().add(cartDetail);
        gioHang.setTongSoLuong(gioHang.getTongSoLuong() + soLuong);
        gioHang.setTongGia(gioHang.getTongGia().add(giaTungSanPham.multiply(BigDecimal.valueOf(soLuong))));

        // Save the GioHang entity (cascading will save the new ChiTietGioHang entity)
        gioHangRepository.save(gioHang);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartDetailsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCart(@PathVariable Long id, @RequestBody GioHangChiTietDTO cartDetailsDTO) {
        Optional<ChiTietGioHang> optionalCartDetail = chiTietGioHangRepository.findById(id);

        if (!optionalCartDetail.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart detail not found");
        }

        ChiTietGioHang cartDetail = optionalCartDetail.get();
        GioHang cart = cartDetail.getGioHang();

        int quantityDifference = cartDetail.getSoLuong() - cartDetailsDTO.getSoLuong();
        if (cartDetailsDTO.getSoLuong() == 0) {
            // Delete the cart detail if the quantity is 0
            chiTietGioHangRepository.delete(cartDetail);
            cart.getCartDetails().remove(cartDetail);
        } else {
            // Update the cart detail if the quantity is not 0
            cartDetail.setSoLuong(cartDetailsDTO.getSoLuong());
            cart.getCartDetails().add(cartDetail);
        }

        // Update the cart's total quantity and total price
        cart.setTongSoLuong(cart.getTongSoLuong() - quantityDifference);
        cart.setTongGia(cart.getTongGia()
                .subtract(cartDetail.getGiaTungSanPham().multiply(BigDecimal.valueOf(quantityDifference))));

        gioHangRepository.save(cart);

        return ResponseEntity.status(HttpStatus.OK).body(cartDetailsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable Long id) {
        Optional<ChiTietGioHang> optionalCartDetail = chiTietGioHangRepository.findById(id);

        if (!optionalCartDetail.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart detail not found");
        }

        ChiTietGioHang cartDetail = optionalCartDetail.get();
        GioHang cart = cartDetail.getGioHang();

        chiTietGioHangRepository.delete(cartDetail);
        cart.getCartDetails().remove(cartDetail);
        cart.setTongSoLuong(cart.getTongSoLuong() - cartDetail.getSoLuong());
        cart.setTongGia(cart.getTongGia()
                .subtract(cartDetail.getGiaTungSanPham().multiply(BigDecimal.valueOf(cartDetail.getSoLuong()))));
        gioHangRepository.save(cart);

        return ResponseEntity.status(HttpStatus.OK).body("Cart detail deleted successfully");
    }

}
