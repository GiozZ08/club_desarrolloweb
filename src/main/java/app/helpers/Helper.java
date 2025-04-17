package app.helpers;

import app.dto.AdministradorDto;
import app.dto.ConsumoDto;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.MembresiaDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.model.Administrador;
import app.model.Consumo;
import app.model.Guest;
import app.model.Invoice;
import app.model.InvoiceDetail;
import app.model.Membresia;
import app.model.Partner;
import app.model.Person;

/**
 * Clase de utilidad para convertir entre modelos y DTOs.
 */
public class Helper {

    // Método para convertir de Person a PersonDto
    public static PersonDto parse(Person person) {
        if (person == null) return null;

        return new PersonDto(
            person.getId(),
            person.getDocument(),  // Orden corregido
            person.getName(),
            person.getUserName(),
            person.getPassword(),
            person.getUserType()   // Este es el parámetro que faltaba
        );
        
    }

    // Método para convertir de PersonDto a Person
    public static Person parse(PersonDto personDto) {
        if (personDto == null) return null;

        Person person = new Person();
        person.setId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setPassword(personDto.getPassword());
        person.setName(personDto.getName());
        person.setUserName(personDto.getUserName());
        person.setUserType(personDto.getUserType());
        return person;
    }

    // Método para convertir de Administrador a AdministradorDto
    public static AdministradorDto parse(Administrador administrador) {
        if (administrador == null) return null;

        AdministradorDto administradorDto = new AdministradorDto();
        administradorDto.setId(administrador.getId());
        administradorDto.setDocument(administrador.getDocument());
        administradorDto.setName(administrador.getName());
        administradorDto.setUserName(administrador.getUserName());
        administradorDto.setUserType(administrador.getUserType());
        administradorDto.setNivelAcceso(administrador.getNivelAcceso());
        administradorDto.setDepartamento(administrador.getDepartamento());
        return administradorDto;
    }

    // Método para convertir de AdministradorDto a Administrador
    public static Administrador parse(AdministradorDto administradorDto) {
        if (administradorDto == null) return null;

        Administrador administrador = new Administrador();
        administrador.setId(administradorDto.getId());
        administrador.setDocument(administradorDto.getDocument());
        administrador.setName(administradorDto.getName());
        administrador.setUserName(administradorDto.getUserName());
        administrador.setUserType(administradorDto.getUserType());
        administrador.setNivelAcceso(administradorDto.getNivelAcceso());
        administrador.setDepartamento(administradorDto.getDepartamento());
        return administrador;
    }

    // Método para convertir de Partner a PartnerDto
    public static PartnerDto parse(Partner partner) {
        if (partner == null) return null;

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partner.getId());
        partnerDto.setDocument(partner.getDocument());
        partnerDto.setName(partner.getName());
        partnerDto.setUserName(partner.getUserName());
        partnerDto.setUserType(partner.getUserType());
        partnerDto.setIdMembresia(partner.getIdMembresia()); 
        return partnerDto;
    }

    // Método para convertir de PartnerDto a Partner
    public static Partner parse(PartnerDto partnerDto) {
        if (partnerDto == null) return null;

        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        partner.setDocument(partnerDto.getDocument());
        partner.setName(partnerDto.getName());
        partner.setUserName(partnerDto.getUserName());
        partner.setUserType(partnerDto.getUserType());
        partner.setIdMembresia(partnerDto.getIdMembresia());
        return partner;
    }

    // Método para convertir de Guest a GuestDto
    public static GuestDto parse(Guest guest) {
        if (guest == null) return null;

        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setDocument(guest.getDocument());
        guestDto.setName(guest.getName());
        guestDto.setUserName(guest.getUserName());
        guestDto.setUserType(guest.getUserType());
        guestDto.setNombre(guest.getNombre());
        guestDto.setApellido(guest.getApellido());
        guestDto.setFechaInvitacion(guest.getFechaInvitacion());
        guestDto.setFechaRegistro(guest.getFechaRegistro());
        guestDto.setReferidoPor(parse(guest.getReferidoPor())); // Convertir Partner a PartnerDto
        guestDto.setStatus(guest.isStatus());
        return guestDto;
    }

    // Método para convertir de GuestDto a Guest
    public static Guest parse(GuestDto guestDto) {
        if (guestDto == null) return null;

        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setDocument(guestDto.getDocument());
        guest.setName(guestDto.getName());
        guest.setUserName(guestDto.getUserName());
        guest.setUserType(guestDto.getUserType());
        guest.setNombre(guestDto.getNombre());
        guest.setApellido(guestDto.getApellido());
        guest.setFechaInvitacion(guestDto.getFechaInvitacion());
        guest.setFechaRegistro(guestDto.getFechaRegistro());
        guest.setReferidoPor(parse(guestDto.getReferidoPor())); // Convertir PartnerDto a Partner
        guest.setStatus(guestDto.isStatus());
        return guest;
    }

    public static Consumo parse(ConsumoDto consumoDto) {
        if (consumoDto == null) return null;
        
        Consumo consumo = new Consumo();
        consumo.setIdConsumo(consumoDto.getIdConsumo());
        consumo.setFechaConsumo(consumoDto.getFechaConsumo());
        consumo.setNombreProducto(consumoDto.getNombreProducto());
        consumo.setValorUnitario(consumoDto.getValorUnitario());
        return consumo;
    }

    public static ConsumoDto parse(Consumo consumo) {
        if (consumo == null) return null;
    
        ConsumoDto dto = new ConsumoDto();
        dto.setIdConsumo(consumo.getIdConsumo());
        dto.setFechaConsumo(consumo.getFechaConsumo());
        dto.setNombreProducto(consumo.getNombreProducto());
        dto.setValorUnitario(consumo.getValorUnitario());
        return dto;
    }

    public static InvoiceDto parse(Invoice invoice) {
        if (invoice == null) return null;
        
        InvoiceDto dto = new InvoiceDto();
        dto.setId(invoice.getId());
        // Convertir la entidad Person a PersonDto usando el método parse de Helper
        dto.setSocio(Helper.parse(invoice.getSocio()));
        dto.setFechaFactura(invoice.getFechaFactura());
        dto.setValorTotal(invoice.getValorTotal());
        dto.setMetodoPago(invoice.getMetodoPago());
        dto.setFechaPago(invoice.getFechaPago());
        dto.setStatus(invoice.isStatus());
        return dto;
    }
    
    // Método para convertir de InvoiceDto a Invoice
    public static Invoice parse(InvoiceDto invoiceDto) {
        if (invoiceDto == null) return null;
        
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        // Convertir el PersonDto a Person usando el método parse de Helper
        invoice.setSocio(Helper.parse(invoiceDto.getSocio()));
        invoice.setFechaFactura(invoiceDto.getFechaFactura());
        invoice.setValorTotal(invoiceDto.getValorTotal());
        invoice.setMetodoPago(invoiceDto.getMetodoPago());
        invoice.setFechaPago(invoiceDto.getFechaPago());
        invoice.setStatus(invoiceDto.isStatus());
        return invoice;
    }

    public static InvoiceDetail parse(InvoiceDetailDto invoiceDetailDto) {
        if (invoiceDetailDto == null) {
            return null;
        }
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setId(invoiceDetailDto.getId());
        // Se asume que existen métodos parse para convertir InvoiceDto y ConsumoDto a sus respectivas entidades
        invoiceDetail.setInvoice(Helper.parse(invoiceDetailDto.getInvoice()));
        invoiceDetail.setConsumo(Helper.parse(invoiceDetailDto.getConsumo()));
        invoiceDetail.setCantidad(invoiceDetailDto.getCantidad());
        invoiceDetail.setPrecioUnitario(invoiceDetailDto.getPrecioUnitario());
        return invoiceDetail;
    }

    public static InvoiceDetailDto parse(InvoiceDetail invoiceDetail) {
        if (invoiceDetail == null) {
            return null;
        }
        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setId(invoiceDetail.getId());
        // Se asume que existen métodos parse para convertir InvoiceDto y ConsumoDto a sus respectivas entidades
        invoiceDetailDto.setInvoice(Helper.parse(invoiceDetail.getInvoice()));
        invoiceDetailDto.setConsumo(invoiceDetail.getConsumo() != null ? Helper.parse(invoiceDetail.getConsumo()) : null);
        invoiceDetailDto.setCantidad(invoiceDetail.getCantidad());
        invoiceDetailDto.setPrecioUnitario(invoiceDetail.getPrecioUnitario());
     
        return invoiceDetailDto;
    }


     public static MembresiaDto parse(Membresia membresia) {
        if (membresia == null) {
            return null;
        }
        MembresiaDto dto = new MembresiaDto();
        dto.setIdMembresia(membresia.getIdMembresia());
        dto.setEstado(membresia.getEstado());
        dto.setFondoActual(membresia.getFondoActual());
        dto.setLimiteFondo(membresia.getLimiteFondo());
        dto.setTipoMembresia(membresia.getTipoMembresia());
        return dto;
    }

    public static Membresia parse(MembresiaDto membresiaDto) {
        if (membresiaDto == null) {
            return null;
        }
        Membresia memb = new Membresia();
        memb.setIdMembresia(membresiaDto.getIdMembresia());
        memb.setEstado(membresiaDto.getEstado());
        memb.setFondoActual(membresiaDto.getFondoActual());
        memb.setLimiteFondo(membresiaDto.getLimiteFondo());
        memb.setTipoMembresia(membresiaDto.getTipoMembresia());
        return memb;
    }
}


    
    

    
    

