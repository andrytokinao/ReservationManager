package com.kinga.microservice.reservation.controllers;


import com.kinga.microservice.reservation.ReservationService;
import com.kinga.microservice.reservation.commands.VoyageurForm;
import com.kinga.microservice.reservation.converters.FromVoyageur;
import com.kinga.microservice.reservation.domain.Voyageur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class ReservationController {
    private ReservationService reservationService;

    private FromVoyageur fromVoyageur;

    @Autowired
    public void setvoyageurTovoyageurForm(FromVoyageur fromVoyageur) {
        this.fromVoyageur = fromVoyageur;
    }

    @Autowired
    public void setvoyageurService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping("/")
    public String redirToList(){
            return "redirect:/reservation/list";
    }

    @RequestMapping({"/reservation/list", "/reservation"})
    public String listvoyageurs(Model model){
        model.addAttribute("voyageurs", reservationService.listAll());
        return "reservation/list";
    }

    @RequestMapping("/reservation/show/{id}")
    public String getvoyageur(@PathVariable String id, Model model){
        model.addAttribute("voyageur", reservationService.getById(id));
        return "voyageur/show";
    }

    @RequestMapping("reservation/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Voyageur voyageur = reservationService.getById(id);
        VoyageurForm voyageurForm = fromVoyageur.convert(voyageur);

        model.addAttribute("voyageurForm", voyageurForm);
        return "reservation/voyageurform";
    }

    @RequestMapping("/voyageur/new")
    public String newvoyageur(Model model){
        model.addAttribute("voyageurForm", new VoyageurForm());
        return "reservation/voyageurform";
    }

    @RequestMapping(value = "/voyageur", method = RequestMethod.POST)
    public String saveOrUpdatevoyageur(@Valid VoyageurForm voyageurForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "reservation/voyageurform";
        }

        Voyageur savedVoyageur = reservationService.saveOrUpdatevoyageurForm(voyageurForm);

        return "redirect:/reservation/show/" + savedVoyageur.getId();
    }

    @RequestMapping("/reservation/delete/{id}")
    public String delete(@PathVariable String id){
        reservationService.delete(id);
        return "redirect:/reservation/list";
    }
}