package com.example.MecidiyekoyAnketAS;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class SurveyAppController {
    @Autowired
    private FootballRepo footballRepo;

    @Autowired
    private HappinessRepo happinessRepo;

    public int getAvgHappiness() {
        Long count = happinessRepo.count();
        if(count == 0) return 0;
        int totalHappiness = happinessRepo.findAll().stream()
            .map(HappinessSurvey::getHapiness)
            .mapToInt(Integer::intValue)
            .sum();
        double percentage = ((double) totalHappiness / (count * 10)) * 100;
        return (int) percentage;
    }

    public Map<String, Integer> getTeamPercentage() {
        List<FootballSurvey> footballSurveys = footballRepo.findAll();
        int total = footballSurveys.size();
        Map<String, Long> counts = footballSurveys.stream()
            .collect(Collectors.groupingBy(FootballSurvey::getTeam, Collectors.counting()));
        Map<String, Integer> percentages = new LinkedHashMap<>();
        for(Map.Entry<String, Long> entry : counts.entrySet()) {
            int percentage = (int) Math.round((entry.getValue() / (double) total) * 100);
            percentages.put(entry.getKey(), percentage);
        }
        return percentages;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("happinessPercentage", getAvgHappiness());
        model.addAttribute("teamPercentage", getTeamPercentage());
        return "index";
    }

    @GetMapping("/completed-forms")
    public String completedForms(Model model) {
        model.addAttribute("footballSurveys", footballRepo.findAll());
        model.addAttribute("happinessSurveys", happinessRepo.findAll());
        return "completed-forms";
    }

    @RequestMapping("/completed-forms/football/{id}")
    public String footballDetail(@PathVariable Integer id, Model model) {
        FootballSurvey survey = footballRepo.findById(id).orElse(null);
        model.addAttribute("footballId", survey);
        return "football-detail";
    }

    @GetMapping("/completed-forms/football/{id}/edit")
    public String footballEdit(@PathVariable Integer id, Model model) {
        FootballSurvey survey = footballRepo.findById(id).orElse(null);
        model.addAttribute("footballId", survey);
        return "editFSurvey";
    }

    @PostMapping("/completed-forms/football/{id}/edit")
    public String footballEditPost(@PathVariable Integer id, @ModelAttribute FootballSurvey footballSurvey) {
        footballRepo.save(footballSurvey);
        return "redirect:/completed-forms/football/{id}";
    }

    @GetMapping("/completed-forms/football/{id}/delete")
    public String footballDelete(@PathVariable Integer id) {
        footballRepo.deleteById(id);
        return "redirect:/completed-forms";
    }

    @RequestMapping("/completed-forms/happiness/{id}")
    public String happinessDetail(@PathVariable Integer id, Model model) {
        HappinessSurvey survey = happinessRepo.findById(id).orElse(null);
        model.addAttribute("happinessId", survey);
        return "happiness-detail";
    }

    @GetMapping("/completed-forms/happiness/{id}/edit")
    public String happinessEdit(@PathVariable Integer id, Model model) {
        HappinessSurvey survey = happinessRepo.findById(id).orElse(null);
        model.addAttribute("happinessId", survey);
        return "editHSurvey";
    }

    @PostMapping("/completed-forms/happiness/{id}/edit")
    public String happinessEditPost(@PathVariable Integer id, @ModelAttribute HappinessSurvey happinessSurvey) {
        happinessRepo.save(happinessSurvey);
        return "redirect:/completed-forms/happiness/{id}";
    }

    @GetMapping("/completed-forms/happiness/{id}/delete")
    public String happinessDelete(@PathVariable Integer id) {
        happinessRepo.deleteById(id);
        return "redirect:/completed-forms";
    }

    @GetMapping("/anket/football")
    public String footballSurvey(Model model) {
        model.addAttribute("footballSurvey", new FootballSurvey());
        return "football";
    }

    @PostMapping("/anket/football")
    public String footballSurveyPost(@ModelAttribute FootballSurvey footballSurvey) {
        footballRepo.save(footballSurvey);
        return "send";
    }

    @GetMapping("/anket/happiness")
    public String happinessSurvey(Model model) {
        model.addAttribute("happinessSurvey", new HappinessSurvey());
        return "hapiness";
    }

    @PostMapping("/anket/happiness")
    public String happinessSurveyPost(@ModelAttribute HappinessSurvey happinessSurvey) {
        happinessRepo.save(happinessSurvey);
        return "send";
    }
}
