package com.jdc.clinic.controller.partner;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.services.BookingService;

@Controller
@RequestMapping("/partner/bookings")
public class BookingController {

	@Autowired
	BookingService bService;

	/*
	 * @GetMapping public String index(@PathVariable int clinicID, ModelMap model) {
	 * 
	 * return "/views/partner/bookings"; }
	 */

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		// TODO check Login user or not
		HttpSession session = request.getSession(true);

		model.put("clinic", bService.getClinicByPhone(((Account) session.getAttribute("loginUser")).getPhone()));

		model.put("doctors", bService.findDoctors());

		model.put("bookings", bService.listAllBookings());

		/*
		 * model.put("bookings", bService.getBookingByPartnerAndDate((((Partner)
		 * session.getAttribute("partner")).getPhone())));
		 */
		return "/views/partner/bookings";
	}

	@GetMapping("/{date}")
	public String showPartnerBookingByDate(@PathVariable("date") String date, ModelMap model) {
		model.put("bookings", bService.getPartnerBookingByDate("partner", LocalDate.parse(date)));
		return "/views/partner/bookings";
	}

	@GetMapping("/search")
	public String search(@PathVariable int clinicID, @RequestParam("pname") String pname, ModelMap model) {
		// TODO Need To consider search parameters
		return "/views/partner/bookings";
	}

	/*
	 * @GetMapping("/{bookid}") public String findById(@PathVariable int
	 * clinicID, @PathVariable int bookid, ModelMap model) {
	 * 
	 * return "/views/partner/patient"; }
	 */

	/*
	 * @PostMapping("/{bookid}") public String cancel(@PathVariable int
	 * clinicID, @PathVariable int bookid) {
	 * bService.delete(bService.findById(bookid).get());
	 * 
	 * return String.format("redirect:/partner/bookings/%d", bookid); return
	 * "redirect:/partner/bookings"; }
	 */

	/*
	 * @GetMapping("/{id}") public String cancel(@PathVariable("id") long bookid) {
	 * bService.delete(bService.findById(bookid).get());
	 * 
	 * return String.format("redirect:/partner/bookings/%d", bookid); return
	 * "redirect:/partner/bookings"; }
	 */
}
