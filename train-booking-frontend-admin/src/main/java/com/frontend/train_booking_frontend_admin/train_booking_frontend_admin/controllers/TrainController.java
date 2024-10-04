package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TrainService;

@Controller
@RequestMapping("/train")
public class TrainController {
	@Autowired
	private TrainService trainService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Train> trains = trainService.getAllTrains();

		model.addAttribute("page", "train").addAttribute("trains", trains);

		return "train/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "train");

		return "train/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() Train train, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (trainService.addTrain(train)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới tàu thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới tàu thất bại!");
		}
		return "redirect:/train/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Train train = trainService.getTrainById(id);

		model.addAttribute("page", "train").addAttribute("train", train);

		return "train/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute Train train, BindingResult result,
			RedirectAttributes redirectAttributes) {
		train.setId(id);
		if (trainService.updatetrain(train)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật tàu thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật tàu thất bại!");
		}
		return "redirect:/train/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTrain(@PathVariable Integer id) {
		if (trainService.deletetrain(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa tàu.");
		}
	}
}
