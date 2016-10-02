(ns sh.roosta.gallery.resources)

(def items
  [
   {:id 1 :width 411 :height 664 :title "Baby" :file "baby.jpg" :category :painting :desc "Oil painting of some baby, attempting a diverse colorscheme"}
   {:id 2 :width 993 :height 715 :title "Baptism" :file "baptism.jpg" :category :painting :desc "Watercolor baptism scene"}
   {:id 3 :width 961 :height 343 :title "Cake" :file "cake.jpg" :category :design :desc "One of several design suggestions for a webpage header"}
   {:id 4 :width 466 :height 475 :title "Capucha" :file "capucha.jpg" :category :vector :desc "Inspired by anime ghost figures, early vector attempt"}
   {:id 5 :width 549 :height 908 :title "Charcoal Nomad" :file "charnom.jpg" :category :design :desc "Charcoal masked nomad figure"}
   {:id 6 :width 667 :height 975 :title "Cloak in Motion" :file "cloak.jpg" :category :photo :desc "One is a series of dark room photographs experimenting with various light exposure settings during development"}
   {:id 7 :width 640 :height 82 :gif true :title "Conch" :file "conch.gif" :category :game :desc "Enemy design for an unfinished untitled shmup game"}
   {:id 8 :width 819 :height 1386 :title "Post cover" :file "cover.jpg" :category :painting :desc "Conseptual cover for a comic book that was never finished"}
   {:id 9 :width 437 :height 736 :title "Kanal Session Design" :file "cs.jpg" :category :design :desc "One of several design suggestions for a skating event. Not used but I still like it"}
   {:id 10 :width 1164 :height 785 :title "Drainpipes" :file "drainpipes.jpg" :category :photo :desc "Dark room development"}
   {:id 11 :width 1017 :height 723  :title "Dunes" :file "dunes.jpg" :category :painting :desc "Sand dunes abstracted"}
   {:id 12 :width 376 :height 520 :title "Masked Pencil Sketch" :file "edn.jpg" :category :drawing :desc "More pencil drawings of a gass mask wearing figure"}
   {:id 13 :width 121 :height 104 :gif true :title "Pixel Explosion" :file "expl.gif" :category :game :desc "Explosion from an unfinished shmup game"}
   {:id 14 :width 184 :height 358 :title "Floating Island" :file "flisland.png" :category :game :desc "Floating island for an unfinished shmup game"}
   {:id 15 :width 1193 :height 929 :title "Development fluid experiment" :file "fluid.jpg" :category :photo :desc "Messing around with development fluid that turned out kinda neat"}
   {:id 16 :width 711 :height 711  :title "Goat Boys LP cover design" :file "gbcover.jpg" :category :design :desc "Cover design for Goat Boys debut album" :link "https://open.spotify.com/artist/2GadG4cFR4bNH6fyLFt8JK"}
   {:id 17 :width 497 :height 635 :title "Ghost Guy" :file "ghost.jpg" :category :vector :desc "Early vector attempt at a ghost like creature inspired by anime/games and the masks ghosts wear"}
   {:id 18 :width 548 :height 320 :title "Gray Brother" :file "graybrother.jpg" :category :drawing :desc "Pencil drawing of a gass masked wearing person"}
   {:id 19 :width 1024 :height 576 :title "Hooded Moon" :file "hoodie.jpg" :category :design :desc "Cover suggestion for a band, that was never used"}
   {:id 20 :width 720 :height 470 :title "Hufda" :file "hufda.jpg" :category :design :desc "Cover suggestion for a band, it that was never used"}
   {:id 21 :width 779 :height 1087  :title "Selv Portrait with background" :file "inkdanfull.jpg" :category :painting :desc "Selv portrait with a superimposed background"}
   {:id 22 :width 625 :height 859 :title "Selv Portrait" :file "inkdans.jpg" :category :painting :desc "Selv portrait using ink on plastic"}
   {:id 23 :width 486 :height 566 :title "Insectlike" :file "insectlike.jpg" :category :drawing :desc "One of several inksect inspired mask drawings"}
   {:id 24 :width 609 :height 924 :title "Julie series nr 1" :file "js1.jpg" :category :photo :desc "First in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 26 :width 592 :height 869 :title "Julie series nr 3" :file "js3.jpg" :category :photo :desc "Third in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 27 :width 543 :height 827 :title "Julie series nr 4" :file "js4.jpg" :category :photo :desc "Forth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 28 :width 574 :height 854 :title "Julie series nr 5" :file "js5.jpg" :category :photo :desc "Fifth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 29 :width 865 :height 571 :title "Julie series nr 6" :file "js6.jpg" :category :photo :desc "Sixth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 30 :width 952 :height 1332 :title "Kodak Ice" :file "kodak.jpg" :category :photo :desc "Failed to develop this macro image of some ice but the result I still like"}
   {:id 31 :width 549 :height 731 :title "Desert Lady" :file "lady.jpg" :category :painting :desc "Unfinished painting trying to depict a desert-like dress style"}
   {:id 32 :width 489 :height 741 :title "Candle lamp" :file "lamp.jpg" :category :diy :desc "Lamp made for christmas using laser cutter, paint and patience. See link for vector blueprint" :link "https://roosta.sh/gallery/img/lamp_vector.svg"}
   {:id 33 :width 480 :height 480 :title "Monoxieman Idle" :file "monoxieman_idle.gif" :category :game :desc "Idle animation character sprite for unfinished game"}
   {:id 34 :width 480 :height 480 :title "Monoxieman Jump" :file "monoxieman_jump.gif" :category :game :desc "Jump animation character sprite for unfinished game"}
   {:id 35 :width 480 :height 480 :title "Monoxieman Running" :file "monoxieman_running.gif" :category :game :desc "Running animation character sprite for unfinished game"}
   {:id 36 :width 592 :height 800 :title "More ink!" :file "moreink.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 37 :width 592 :height 800 :title "More mask!" :file "moremask.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 38 :width 624 :height 788 :title "Nomad Angle" :file "nomad-ang.jpg" :category :painting :desc "Titular nomad drawn from an angle. Comic book concept"}
   {:id 39 :width 638 :height 878 :title "Nomad Equipped" :file "nomad-eq.jpg" :category :drawing :desc "Nomad with equipment, comic book concept"}
   {:id 40 :width 592 :height 725 :title "Ouroboros" :file "ouroboros.jpg" :category :drawing :desc "Unfinished sketch of ouroboros"}
   {:id 41 :width 752 :height 696 :title "Pattern Composition" :file "pattern_comp.jpg" :category :drawing :desc "One of many patterns inspired loosely on the art nouveau style"}
   {:id 42 :width 542 :height 769 :title "Pray Pencil" :file "pray_pencil.jpg" :category :drawing :desc "Sketch of two praying figures"}
   {:id 43 :width 1545 :height 380 :title "House gardener logo suggestion" :file "precore.png" :category :vector :desc "Logo suggestion for a design/gardening company. Not used but I still like it"}
   {:id 44 :width 598 :height 877 :title "Runny ink portrait" :file "riportrait.jpg" :category :painting :desc "Self portrait with gratuitous use of ink"}
   {:id 45 :width 610 :height 873 :title "Salt" :file "salt.jpg" :category :painting :desc "Salt/sand dunes abstracted illustration"}
   {:id 46 :width 592 :height 800 :title "Sectered" :file "secterd.jpg" :category :painting :desc "Gass mask wearing kid in the series of insect inspired gass masks"}
   {:id 47 :width 851 :height 315 :title "Shmup Screenshot" :file "shmup.png" :category :game :desc "Composit unfinished shmup screenshot from an unfinished untitled game"}
   {:id 48 :width 1019 :height 723 :title "Sea of sand" :file "sos.jpg" :category :painting :desc "Mountain surrounded by sand. Looks in hindsight more like water"}
   {:id 49 :width 850 :height 1163 :title "Splotch" :file "splotch.jpg" :category :painting :desc "Watercolor and ink drawing of a gassmask wearing creature"}
   {:id 50 :width 779 :height 566 :title "Stones study" :file "stones.jpg" :category :painting :desc "Study of stone texture, watercolor"}
   {:id 51 :width 872 :height 461 :title "Thirst" :file "thirst.jpg" :category :painting :desc "Thirsty fellow drawn with ink on rice paper"}
   {:id 52 :width 875 :height 593 :title "Three men" :file "tmen.jpg" :category :painting :desc "Card playing fellows drawn with ink on rice paper"}
   {:id 53 :width 600 :height 600 :title "VLN Cover suggestion" :file "vln.jpg" :category :design :desc "Pixel art cover suggestion for a band album"}
   {:id 54 :width 648 :height 628 :title "Wind Cover" :file "wind.jpg" :category :design :desc "Cover for a 60s inspired jam band. Marker on plastic"}
   {:id 55 :width 542 :height 762 :title "Wrap" :file "wrap.jpg" :category :drawing :desc "Cloth study, pencil"}
   ])
